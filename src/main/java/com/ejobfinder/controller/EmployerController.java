package com.ejobfinder.controller;

import com.ejobfinder.model.*;
import com.ejobfinder.model.rules.PerfectEmployeeRules;
import com.ejobfinder.service.CandidateService;
import com.ejobfinder.service.EmployerService;
import com.ejobfinder.service.JobOfferService;
import com.ejobfinder.service.LocationService;
import com.ejobfinder.utils.consts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class EmployerController {

    private Path path;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private JobOfferService jobOfferService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private PerfectEmployeeRules perfectEmployeeRules;

    @RequestMapping("/employer/jobOfferInventory")
    public String employerPageInventory(@AuthenticationPrincipal User activeUser, Model model) {
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());
        List<JobOffer> jobOffers = jobOfferService.getJobOffersByEmployerName(activeUser.getUsername());
        model.addAttribute("jobOffers", jobOffers);
        model.addAttribute("isPremium", employer.getPremiumMember());

        return "jobOfferInventory";
    }

    @RequestMapping("/employer")
    public String employerPage(Model model, @AuthenticationPrincipal User activeUser) {
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());

        model.addAttribute("name", employer.getName());
        model.addAttribute("lastName", employer.getLastName());
        model.addAttribute("isPremium", employer.getPremiumMember());

        AtomicBoolean notificationNeeded = new AtomicBoolean(false);
        AtomicBoolean updateOffer = new AtomicBoolean(false);
        List<JobOffer> jobOffers = jobOfferService.getJobOffersByEmployerName(activeUser.getUsername());

        if (jobOffers != null) {
            for (JobOffer offer : jobOffers) {
                updateOffer.set(false);
                for (JobOfferApplication application : offer.getAllJobOfferApplications()) {
                    if (application.getNotify()) {
                        application.setNotify(false);
                        notificationNeeded.set(true);
                    }
                }
                if (updateOffer.get()) {
                    jobOfferService.updateJobOffer(offer);
                }
            }
        }

        model.addAttribute("notify", notificationNeeded.get());
        model.addAttribute("offers", jobOffers);

        return "employer";
    }

    @RequestMapping("/employer/editProfilePage")
    public String editProfilePage(@AuthenticationPrincipal User activeUser, Model model) {
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());

        model.addAttribute("employerProfile", employer);

        return "editProfile";
    }

    @RequestMapping(value = "/employer/updateMyProfile", method = RequestMethod.POST)
    public String updateProfile(@Valid @ModelAttribute("employerProfile") Employer updatedProfile, BindingResult result, Model model,
                                HttpServletRequest request, @AuthenticationPrincipal User activeUser) {
        Employer employerFromDB = employerService.getEmployerByUsername(activeUser.getUsername());

        if (result.hasErrors()) {
            return "editProfile";
        }

        employerFromDB.setCompanyName(updatedProfile.getCompanyName());
        employerFromDB.setEmployerPhone(updatedProfile.getEmployerPhone());
        employerFromDB.setEmployerEmail(updatedProfile.getEmployerEmail());
        employerFromDB.setName(updatedProfile.getName());
        employerFromDB.setLastName(updatedProfile.getLastName());

        employerService.updateEmployer(employerFromDB);

        return "redirect:/employer/";
    }

    @RequestMapping("/employer/buyPremium")
    public String buyPremium(Model model, @AuthenticationPrincipal User activeUser) {
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());

        employer.setPremiumMember(true);
        model.addAttribute("name", employer.getName());
        model.addAttribute("lastName", employer.getLastName());
        model.addAttribute("isPremium", employer.getPremiumMember());

        employerService.updateEmployer(employer);

        List<JobOffer> jobOffers = jobOfferService.getJobOffersByEmployerName(activeUser.getUsername());
        boolean notificationNeeded = jobOffers != null && jobOffers.stream().anyMatch(jobOffer -> jobOffer.getJobOfferApplications().stream().anyMatch(application ->
                application.getEmployerAcceptancee() != null &&
                        application.getCandidateAcceptancee() != application.getEmployerAcceptancee()
                        && application.getCandidateAcceptancee()
        ));

        model.addAttribute("notify", notificationNeeded);
        model.addAttribute("offers", jobOffers);

        return "employer";
    }

    @RequestMapping(value = "/employer/viewCV/{candidateId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getPDF1(@PathVariable String candidateId) {

        Candidate candidate = candidateService.getCandidateById(Integer.parseInt(candidateId));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf"));


        headers.add("content-disposition", "inline;filename=" + "cvFile");

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(candidate.getCvFIle(), headers, HttpStatus.OK);
        return response;
    }

    @RequestMapping("/employer/cancelPremium")
    public String cancelPremium(Model model, @AuthenticationPrincipal User activeUser) {
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());

        employer.setPremiumMember(false);
        model.addAttribute("name", employer.getName());
        model.addAttribute("lastName", employer.getLastName());
        model.addAttribute("isPremium", employer.getPremiumMember());

        employerService.updateEmployer(employer);

        List<JobOffer> jobOffers = jobOfferService.getJobOffersByEmployerName(activeUser.getUsername());
        boolean notificationNeeded = jobOffers != null && jobOffers.stream().anyMatch(jobOffer -> jobOffer.getJobOfferApplications().stream().anyMatch(application ->
                application.getEmployerAcceptancee() != null &&
                        application.getCandidateAcceptancee() != application.getEmployerAcceptancee()
                        && application.getCandidateAcceptancee()
        ));

        model.addAttribute("notify", notificationNeeded);
        model.addAttribute("offers", jobOffers);

        return "employer";
    }

    @RequestMapping("/employer/jobOfferInventory/addJobOffer")
    public String addJobOffer(@AuthenticationPrincipal User activeUser, Model model) {
        JobOffer jobOffer = new JobOffer();
        Location location = new Location();
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());

        jobOffer.setEmployer(employer);

        model.addAttribute("jobOffer", jobOffer);
        model.addAttribute("location", location);
        model.addAttribute("isPremium", employer.getPremiumMember());

        return "addJobOffer";
    }

    @RequestMapping(value = "/employer/jobOfferInventory/addJobOffer", method = RequestMethod.POST)
    public String addJobOfferPost(@Valid @ModelAttribute("jobOffer") JobOffer jobOffer, BindingResult result,
                                  @AuthenticationPrincipal User activeUser, HttpServletRequest request, Model model) {
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());
        jobOffer.setEmployer(employer);
        if (jobOffer.getLocation() != null && jobOffer.getLocation().getCity() != null) {
            Location location = locationService.getLocationByCity(jobOffer.getLocation().getCity());
            if (location != null) {
                jobOffer.setLocation(location);
            }
        }
        if (result.hasErrors()) {
            model.addAttribute("jobOffer", jobOffer);
            model.addAttribute("location", jobOffer.getLocation());
            return "addJobOffer";
        }

        String offerID = jobOfferService.addJobOffer(jobOffer);

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

        return "redirect:/employer/jobOfferInventory/perfectEmployee/" + offerID;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @RequestMapping(value = "/employer/jobOfferInventory/deleteJobOffer/{jobId}")
    public String deleteJobOffer(@PathVariable String jobId, @AuthenticationPrincipal User activeUser, Model model, HttpServletRequest request) {
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());

        model.addAttribute("isPremium", employer.getPremiumMember());
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + jobId + ".png");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        jobOfferService.deleteJobOffer(jobId);

        return "redirect:/employer/jobOfferInventory";
    }

    @RequestMapping("/employer/report")
    public String reportPage(Model model, @AuthenticationPrincipal User activeUser) {
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());

        model.addAttribute("name", employer.getName());
        model.addAttribute("lastName", employer.getLastName());
        model.addAttribute("isPremium", employer.getPremiumMember());

        List<JobOffer> jobOffers = jobOfferService.getJobOffersByEmployerName(activeUser.getUsername());
        boolean notificationNeeded = jobOffers != null && jobOffers.stream().anyMatch(jobOffer -> jobOffer.getJobOfferApplications().stream().anyMatch(application ->
                application.getEmployerAcceptancee() != null &&
                        application.getCandidateAcceptancee() != application.getEmployerAcceptancee() &&
                        application.getCandidateAcceptancee()
        ));

        model.addAttribute("notify", notificationNeeded);
        model.addAttribute("offers", jobOffers);

        return "report";
    }

    @RequestMapping("/employer/jobOfferInventory/editJobOffer/{jobId}")
    public String editJobOffer(@PathVariable("jobId") String jobId, Model model) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);

        model.addAttribute(jobOffer);

        return "editJobOffer";
    }

    @RequestMapping(value = "/employer/jobOfferInventory/editJobOffer", method = RequestMethod.POST)
    public String editProduct(@Valid @ModelAttribute("jobOffer") JobOffer jobOffer, BindingResult result, Model model,
                              HttpServletRequest request, @AuthenticationPrincipal User activeUser) {

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
            Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());
            jobOffer.setEmployer(employer);
        }

        String jobId = jobOfferService.editJobOffer(jobOffer);

        return "redirect:/employer/jobOfferInventory/perfectEmployee/" + jobId;
    }

    @RequestMapping("/employer/jobOfferInventory/perfectEmployee/{jobId}")
    public String perfectEmployee(@PathVariable("jobId") String jobId, @AuthenticationPrincipal User activeUser, Model model, HttpServletRequest request) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());

        perfectEmployeeRules.setJobId(jobId);
        model.addAttribute(jobOffer);
        model.addAttribute("technologies", TechnologiesConst.TECHNOLOGY_LIST);
        model.addAttribute("skills", SkillsConst.SKILL_LIST);
        model.addAttribute("tools", ToolsConst.TOOLS_LIST);
        model.addAttribute("languages", LanguagesConst.LANGUAGE_LIST);
        model.addAttribute("languages_levels", LanguagesConst.LANGUAGE_LEVELS);
        model.addAttribute("locations", LocationsConst.LOCATION_LIST);
        model.addAttribute("workingHours", WorkingHoursConst.VALUES_LIST);
        model.addAttribute("typeOfContracts", TypeOfContractsConst.VALUES_LIST);
        model.addAttribute("periods", PeriodOfNoticesConst.VALUES_LIST);
        model.addAttribute("eduTitles", EducationsConst.PROFESSIONAL_TITLES_LIST);
        model.addAttribute("eduFields", EducationsConst.FIELD_OF_STUDY_LIST);
        model.addAttribute("eduModes", EducationsConst.MODE_OF_STUDY_LIST);
        model.addAttribute("jobTitles", JobTitlesConst.JON_TITLE_LIST);
        model.addAttribute("isPremium", employer.getPremiumMember());

        return "perfectEmployee";
    }

    @RequestMapping("/employer/acceptByEmployer")
    public ResponseEntity<String> jobOfferAccerptByEmployer(@RequestParam String jobID, @RequestParam Integer candidateID, @RequestParam Boolean isAccepted) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobID);

        Optional<JobOfferApplication> application = jobOffer.getAllJobOfferApplications().stream().filter(application1 -> candidateID == application1.getCandidate().getCandidateId()).findFirst();

        if (application.isPresent()) {
            application.get().setEmployerAcceptancee(isAccepted);
            application.get().setNotify(true);
            jobOfferService.updateJobOffer(jobOffer);
        }

        return new ResponseEntity<String>("JobOffer updated", HttpStatus.OK);
    }

}