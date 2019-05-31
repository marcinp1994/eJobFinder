package com.ejobfinder.controller;

import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.Location;
import com.ejobfinder.service.CandidateService;
import com.ejobfinder.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private JobOfferService jobOfferService;

    @Autowired
    private CandidateService candidateService;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/jobOfferList")
    public String getJobOffers(Model model){
        List<JobOffer> jobOffers = jobOfferService.getAllJobOffers();
        model.addAttribute("jobOffers", jobOffers);

        return "jobOfferList";
    }

    @RequestMapping("/jobOfferList/{category}")
    public String getJobOffersForCategory(@PathVariable String category, Model model) {
        List<JobOffer> jobOffersForCategory = jobOfferService.getJobOffersByCategory(category);
        model.addAttribute("jobOffers", jobOffersForCategory);

        return "jobOfferList";
    }

    @RequestMapping("/jobOfferList/viewJobOffer/{jobId}")
    public String viewJobOffer(@PathVariable String jobId, Model model) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String expirationDate = formatter.format(jobOffer.getExpirationDate());
        Location jobLocation = jobOffer.getLocation();
        String location = jobLocation.getCountry().concat(" - ").concat(jobLocation.getCity());

        model.addAttribute(jobOffer);
        model.addAttribute("expirationDate", expirationDate);
        model.addAttribute("location", location);
        return "viewJobOffer";
    }

    @RequestMapping("/candidate/{jobId}/apply")
    public String jobOfferApply(@PathVariable String jobId, Model model, @AuthenticationPrincipal User activeUser) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);
        Candidate candidate = candidateService.getCandidateByUsername(activeUser.getUsername());
        jobOffer.addCandidate(candidate);
        jobOfferService.addJobOffer(jobOffer);
        candidateService.updateCandidate(candidate);

        return "redirect:/";
    }

}
