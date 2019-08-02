package com.ejobfinder.controller;

import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.JobOfferApplication;
import com.ejobfinder.model.Location;
import com.ejobfinder.service.CandidateService;
import com.ejobfinder.service.JobOfferService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public String getJobOffers(Model model) {
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
    public String viewJobOffer(@PathVariable String jobId, Model model, @AuthenticationPrincipal User activeUser) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Candidate candidate = activeUser != null ? candidateService.getCandidateByUsername(activeUser.getUsername()) : null;
        Integer candId = candidate != null ? candidate.getCandidateId() : null;

        String expirationDate = formatter.format(jobOffer.getExpirationDate());
        Location jobLocation = jobOffer.getLocation();
        String location = jobLocation.getCountry().concat(" - ").concat(jobLocation.getCity());

        model.addAttribute(jobOffer);
        model.addAttribute("expirationDate", expirationDate);
        model.addAttribute("location", location);
        model.addAttribute("candidate", candId);


        return "viewJobOffer";
    }

    @RequestMapping("/candidate/{jobId}/apply")
    public String jobOfferApply(@PathVariable String jobId, Model model, @AuthenticationPrincipal User activeUser) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);
        Candidate candidate = activeUser != null ? candidateService.getCandidateByUsername(activeUser.getUsername()) : null;
        JobOfferApplication application = new JobOfferApplication();

        application.setJobOffer(jobOffer);
        application.setCandidate(candidate);
        application.setCandidateAcceptancee(Boolean.TRUE);

        Integer score = candidateService.evaluateScoringOnJobOffer(jobId, candidate);
        Double percent = (double) ((score * 100 / jobOffer.getMaximalPoints()));

        jobOfferService.matchTagsWithCandidateCV(jobOffer, candidate, application);

        application.setPercentOfMaxScore(percent);
        application.setCalculatedScore(score);

        if (jobOffer.getThresholdPercentagePoints() > percent) {
            application.setEmployerAcceptancee(Boolean.FALSE);
        }
        jobOffer.addApplication(application);

        jobOfferService.updateJobOffer(jobOffer);
        // candidateService.updateCandidate(candidate);

        return "redirect:/";
    }

    @RequestMapping("/employer/acceptByEmployer")
    public ResponseEntity<String> jobOfferAccerptByEmployer(@RequestParam String jobID, @RequestParam Integer candidateID, @RequestParam Boolean isAccepted) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobID);

        Optional<JobOfferApplication> application = jobOffer.getAllJobOfferApplications().stream().filter(application1 -> candidateID == application1.getCandidate().getCandidateId()).findFirst();

        if (application.isPresent()) {
            application.get().setEmployerAcceptancee(isAccepted);
            jobOfferService.updateJobOffer(jobOffer);
        }

        return new ResponseEntity<>("Accepted", HttpStatus.OK);
    }

    @RequestMapping("/search")
    public String jobOfferAccerptByEmployer(@RequestParam String searchString, Model model) {
        if (StringUtils.isEmpty(searchString)) {
            return "redirect:/";
        }
        List<JobOffer> jobOffers = jobOfferService.getAllJobOffers();
        Set<JobOffer> result = new HashSet<>();
        String[] searchStrings = searchString.split(" ");

        for (JobOffer offer : jobOffers) {
            boolean containsAll = true;
            for (String key : searchStrings) {
                if (!StringUtils.containsIgnoreCase(offer.getTags(), key)) {
                    containsAll = false;
                }
            }
            if (containsAll) {
                result.add(offer);
            }
        }
        model.addAttribute("jobOffers", result);

        return "jobOfferList";
    }

    @RequestMapping("/candidate/acceptByCandidate")
    public ResponseEntity<String> jobOfferAccerptByCandidate(@RequestParam String jobID, @RequestParam Integer candidateID, @RequestParam Boolean isAccepted) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobID);
        Optional<JobOfferApplication> application = jobOffer.getAllJobOfferApplications().stream().filter(application1 -> candidateID == application1.getCandidate().getCandidateId()).findFirst();

        if (application.isPresent()) {
            application.get().setCandidateAcceptancee(isAccepted);
            if (application.get().getPotential() && application.get().getEmployerAcceptancee()) {
                application.get().setPotential(false);
            }
            jobOfferService.updateJobOffer(jobOffer);
        }
        return new ResponseEntity<>("Accepted", HttpStatus.OK);
    }
}
