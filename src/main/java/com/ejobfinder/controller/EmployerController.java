package com.ejobfinder.controller;

import com.ejobfinder.model.Customer;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.Location;
import com.ejobfinder.service.CustomerService;
import com.ejobfinder.service.JobOfferService;
import com.ejobfinder.service.LocationService;
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
    private CustomerService customerService;

    @Autowired
    private JobOfferService jobOfferService;

    @Autowired
    private LocationService locationService;

    @RequestMapping("/employer/jobOfferInventory")
    public String employerPageInventory(@AuthenticationPrincipal User activeUser, Model model) {
        List<JobOffer> jobOffers = jobOfferService.getJobOffersByCustomerName(activeUser.getUsername());
        model.addAttribute("jobOffers", jobOffers);
        return "jobOfferInventory";
    }

    @RequestMapping("/employer")
    public String employerPage(Model model) {

        return "employer";
    }

    @RequestMapping("/employer/jobOfferInventory/addJobOffer")
    public String addJobOffer(@AuthenticationPrincipal User activeUser, Model model) {
        JobOffer jobOffer = new JobOffer();
        Location location = new Location();
        Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
        jobOffer.setCustomer(customer);
        model.addAttribute("jobOffer", jobOffer);
        model.addAttribute("location", location);

        return "addJobOffer";
    }

    @RequestMapping(value = "/employer/jobOfferInventory/addJobOffer", method = RequestMethod.POST)
    public String addJobOfferPost(@Valid @ModelAttribute("jobOffer") JobOffer jobOffer, BindingResult result,
                                  @AuthenticationPrincipal User activeUser, HttpServletRequest request, Model model) {
        Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
        jobOffer.setCustomer(customer);
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

        jobOfferService.addJobOffer(jobOffer);

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

        if (jobOffer.getCustomer() == null) {
            Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
            jobOffer.setCustomer(customer);
        }

        jobOfferService.editJobOffer(jobOffer);

        return "redirect:/employer/jobOfferInventory";
    }

}
