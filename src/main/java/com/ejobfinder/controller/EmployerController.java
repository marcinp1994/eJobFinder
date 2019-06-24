package com.ejobfinder.controller;

import com.ejobfinder.model.Employer;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.Location;
import com.ejobfinder.model.rules.PerfectEmployeeRules;
import com.ejobfinder.service.EmployerService;
import com.ejobfinder.service.JobOfferService;
import com.ejobfinder.service.LocationService;
import com.ejobfinder.utils.consts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
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

@Controller
public class EmployerController {

    private Path path;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private JobOfferService jobOfferService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private PerfectEmployeeRules perfectEmployeeRules;

    @RequestMapping("/employer/jobOfferInventory")
    public String employerPageInventory(@AuthenticationPrincipal User activeUser, Model model) {
        List<JobOffer> jobOffers = jobOfferService.getJobOffersByEmployerName(activeUser.getUsername());
        model.addAttribute("jobOffers", jobOffers);
        return "jobOfferInventory";
    }

    @RequestMapping("/employer")
    public String employerPage(Model model, @AuthenticationPrincipal User activeUser) {
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());

        model.addAttribute("name", employer.getName());
        model.addAttribute("lastName", employer.getLastName());
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

    @RequestMapping(value = "/employer/jobOfferInventory/deleteJobOffer/{jobId}")
    public String deleteJobOffer(@PathVariable String jobId, @AuthenticationPrincipal User activeUser, Model model, HttpServletRequest request) {

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
    public String perfectEmployee(@PathVariable("jobId") String jobId, Model model, HttpServletRequest request) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);
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

        return "perfectEmployee";
    }

}