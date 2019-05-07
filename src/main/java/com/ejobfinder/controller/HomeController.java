package com.ejobfinder.controller;

import com.ejobfinder.dao.EmployerDao;
import com.ejobfinder.dao.JobOfferDao;
import com.ejobfinder.dao.LocationDao;
import com.ejobfinder.model.Employer;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Le on 1/2/2016.
 */

@Controller
public class HomeController {

    private Path path;

    @Autowired
    private JobOfferDao jobOfferDao;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private EmployerDao employerDao;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/jobOfferList")
    public String getJobOffers(Model model){
        List<JobOffer> jobOffers = jobOfferDao.getAllJobOffers();
        model.addAttribute("jobOffers", jobOffers);

        return "jobOfferList2";
    }

    @RequestMapping("/jobOfferList/viewJobOffer/{jobId}")
    public String viewJobOffer(@PathVariable String jobId, Model model) {
        JobOffer jobOffer = jobOfferDao.getJobOfferById(jobId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String expirationDate = formatter.format(jobOffer.getExpirationDate());
        Location jobLocation = jobOffer.getLocation();
        String location = jobLocation.getCountry().concat(" - ").concat(jobLocation.getCity());

        model.addAttribute(jobOffer);
        model.addAttribute("expirationDate", expirationDate);
        model.addAttribute("location", location);
        return "viewJobOffer";
    }

    @RequestMapping("/employer/{employerId}/jobOfferInventory")
    public String employerPage(@PathVariable String employerId, Model model) {
        List<JobOffer> jobOffers = jobOfferDao.getJobOffersByEmployerId(employerId);
        model.addAttribute("jobOffers", jobOffers);
        model.addAttribute("employerId", employerId);
        return "jobOfferInventory";
    }

    @RequestMapping("/employer/{employerId}/jobOfferInventory/addJobOffer")
    public String addJobOffer(@PathVariable String employerId, Model model) {
        JobOffer jobOffer = new JobOffer();
        Location location = new Location();
        Employer employer = employerDao.getEmployerById(employerId);
        jobOffer.setEmployer(employer);
        model.addAttribute("jobOffer", jobOffer);
        model.addAttribute("location", location);

        return "addJobOffer";
    }

    @RequestMapping(value = "/employer/{employerId}/jobOfferInventory/addJobOffer", method = RequestMethod.POST)
    public String addJobOfferPost(@PathVariable String employerId, @ModelAttribute("jobOffer") JobOffer jobOffer, HttpServletRequest request) {
        Employer employer = employerDao.getEmployerById(employerId);
        jobOffer.setEmployer(employer);
        if (jobOffer.getLocation() != null && jobOffer.getLocation().getCity() != null) {
            Location location = locationDao.getLocationByCity(jobOffer.getLocation().getCity());
            if (location != null) {
                jobOffer.setLocation(location);
            }
        }
        jobOfferDao.addJobOffer(jobOffer);

        MultipartFile companyLogo = jobOffer.getCompanyLogo();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + jobOffer.getJobId() + ".png");

        if (companyLogo != null && !companyLogo.isEmpty()) {
            try {
                companyLogo.transferTo(new File(path.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed", e);
            }
        }

        return "redirect:/employer/" + employerId + "/jobOfferInventory";
    }

    @RequestMapping(value = "/employer/{employerId}/jobOfferInventory/deleteJobOffer/{jobId}")
    public String deleteJobOffer(@PathVariable String employerId, @PathVariable String jobId, Model model, HttpServletRequest request) {

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + jobId + ".png");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        jobOfferDao.deleteJobOffer(jobId);

        return "redirect:/employer/" + employerId + "/jobOfferInventory";
    }

    @RequestMapping("/employer/{employerId}/jobOfferInventory/editJobOffer/{jobId}")
    public String editJobOffer(@PathVariable("jobId") String jobId, @PathVariable String employerId, Model model) {
        JobOffer jobOffer = jobOfferDao.getJobOfferById(jobId);

        model.addAttribute(jobOffer);
        model.addAttribute(employerId);

        return "editJobOffer";
    }

    @RequestMapping(value = "/employer/{employerId}/jobOfferInventory/editJobOffer", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute("jobOffer") JobOffer jobOffer, @PathVariable String employerId, BindingResult result, Model model,
                              HttpServletRequest request) {

        if (result.hasErrors()) {
            return "editJobOffer";
        }

        MultipartFile productImage = jobOffer.getCompanyLogo();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + jobOffer.getJobId() + ".png");

        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception e) {
                throw new RuntimeException("Company logo saving failed", e);
            }
        }

        if (jobOffer.getEmployer() == null) {
            Employer employer = employerDao.getEmployerById(employerId);
            jobOffer.setEmployer(employer);
        }

        jobOfferDao.editJobOffer(jobOffer);

        return "redirect:/employer/" + employerId + "/jobOfferInventory";
    }
}
