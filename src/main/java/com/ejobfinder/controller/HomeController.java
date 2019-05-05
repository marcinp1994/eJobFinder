package com.ejobfinder.controller;

import com.ejobfinder.dao.EmployerDao;
import com.ejobfinder.dao.JobOfferDao;
import com.ejobfinder.model.Employer;
import com.ejobfinder.model.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Le on 1/2/2016.
 */

@Controller
public class HomeController {

    Path path;

    @Autowired
    private JobOfferDao jobOfferDao;

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
    public String viewJobOffer(@PathVariable String jobId, Model model) throws IOException {
        JobOffer jobOffer = jobOfferDao.getJobOfferById(jobId);
        model.addAttribute(jobOffer);
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
        Employer employer = employerDao.getEmployerById(employerId);
        jobOffer.setEmployer(employer);
        model.addAttribute("jobOffer", jobOffer);

        return "addJobOffer";
    }

    @RequestMapping(value = "/employer/{employerId}/jobOfferInventory/addJobOffer", method = RequestMethod.POST)
    public String addJobOfferPost(@PathVariable String employerId, @ModelAttribute("jobOffer") JobOffer jobOffer, HttpServletRequest request) {
        Employer employer = employerDao.getEmployerById(employerId);
        jobOffer.setEmployer(employer);
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
    public String deleteJobOffer(@PathVariable String employerId, @PathVariable String jobId, Model model) {
        jobOfferDao.deleteJobOffer(jobId);
        return "redirect:/employer/" + employerId + "/jobOfferInventory";
    }
}
