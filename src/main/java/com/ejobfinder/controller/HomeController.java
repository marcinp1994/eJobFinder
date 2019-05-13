package com.ejobfinder.controller;

import com.ejobfinder.dao.EmployerDao;
import com.ejobfinder.dao.JobOfferDao;
import com.ejobfinder.dao.LocationDao;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @RequestMapping("/jobOfferList/{category}")
    public String getJobOffersForCategory(@PathVariable String category, Model model) {
        List<JobOffer> jobOffers = jobOfferDao.getAllJobOffers();
        List<JobOffer> jobOffersForCtegory = null;
        for (JobOffer jobOffer : jobOffers) {
            if (category.equalsIgnoreCase(jobOffer.getCategory())) {
                if (jobOffersForCtegory == null) {
                    jobOffersForCtegory = new ArrayList<>();
                }
                jobOffersForCtegory.add(jobOffer);
            }
        }
        model.addAttribute("jobOffers", jobOffersForCtegory);

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

}
