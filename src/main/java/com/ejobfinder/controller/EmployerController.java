package com.ejobfinder.controller;

import com.ejobfinder.dao.EmployerDao;
import com.ejobfinder.dao.JobOfferDao;
import com.ejobfinder.dao.LocationDao;
import com.ejobfinder.model.Employer;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Le on 1/2/2016.
 */

@Controller
public class EmployerController {

    private Path path;

    @Autowired
    private JobOfferDao jobOfferDao;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private EmployerDao employerDao;


    @RequestMapping("/employer/jobOfferInventory")
    public String employerPageInventory(Model model) {
        List<JobOffer> jobOffers = jobOfferDao.getJobOffersByEmployerName(getEmployerName());
        model.addAttribute("jobOffers", jobOffers);
        return "jobOfferInventory";
    }

    @RequestMapping("/employer")
    public String employerPage(Model model) {

        return "employer";
    }

    @RequestMapping("/employer/jobOfferInventory/addJobOffer")
    public String addJobOffer(Model model) {
        JobOffer jobOffer = new JobOffer();
        Location location = new Location();
        Employer employer = employerDao.getEmployerByName(getEmployerName());
        jobOffer.setEmployer(employer);
        model.addAttribute("jobOffer", jobOffer);
        model.addAttribute("location", location);

        return "addJobOffer";
    }

    @RequestMapping(value = "/employer/jobOfferInventory/addJobOffer", method = RequestMethod.POST)
    public String addJobOfferPost(@Valid @ModelAttribute("jobOffer") JobOffer jobOffer, BindingResult result, HttpServletRequest request, Model model) {
        Employer employer = employerDao.getEmployerByName(getEmployerName());
        jobOffer.setEmployer(employer);
        if (jobOffer.getLocation() != null && jobOffer.getLocation().getCity() != null) {
            Location location = locationDao.getLocationByCity(jobOffer.getLocation().getCity());
            if (location != null) {
                jobOffer.setLocation(location);
            }
        }
        if (result.hasErrors()) {
            model.addAttribute("jobOffer", jobOffer);
            model.addAttribute("location", jobOffer.getLocation());
            return "addJobOffer";
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

        return "redirect:/employer/jobOfferInventory";
    }

    @RequestMapping(value = "/employer/jobOfferInventory/deleteJobOffer/{jobId}")
    public String deleteJobOffer(@PathVariable String jobId, Model model, HttpServletRequest request) {

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

        return "redirect:/employer/jobOfferInventory";
    }

    @RequestMapping("/employer/jobOfferInventory/editJobOffer/{jobId}")
    public String editJobOffer(@PathVariable("jobId") String jobId, Model model) {
        JobOffer jobOffer = jobOfferDao.getJobOfferById(jobId);
        model.addAttribute(jobOffer);

        return "editJobOffer";
    }

    @RequestMapping(value = "/employer/jobOfferInventory/editJobOffer", method = RequestMethod.POST)
    public String editProduct(@Valid @ModelAttribute("jobOffer") JobOffer jobOffer, BindingResult result, Model model,
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
            Employer employer = employerDao.getEmployerByName(getEmployerName());
            jobOffer.setEmployer(employer);
        }

        jobOfferDao.editJobOffer(jobOffer);

        return "redirect:/employer/jobOfferInventory";
    }

    private String getEmployerName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
