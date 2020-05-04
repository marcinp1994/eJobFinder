package com.ejobfinder.controller;

import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.Employer;
import com.ejobfinder.service.CandidateService;
import com.ejobfinder.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    private final EmployerService employerService;
    private final CandidateService candidateService;

    @Autowired
    public RegisterController(EmployerService employerService, CandidateService candidateService) {
        this.employerService = employerService;
        this.candidateService = candidateService;
    }

    @RequestMapping("/registerCandidate")
    public String registerCandidate(Model model) {

        Candidate candidate = new Candidate();
        model.addAttribute("candidate", candidate);

        return "registerCandidate";
    }

    @RequestMapping("/registerEmployer")
    public String registerEmployer(Model model) {

        Employer employer = new Employer();
        model.addAttribute("employer", employer);

        return "registerEmployer";
    }

    @RequestMapping(value = "/registerEmp", method = RequestMethod.POST)
    public String registerEmployerPost(@ModelAttribute("employer") Employer employer, HttpServletRequest request) {
        employer.setEnabled(true);
        employerService.addEmployer(employer);
        try {
            request.login(employer.getUsername(), employer.getPassword());
        } catch (ServletException e) {
            return "registerCustomerSuccess";
        }

        return "redirect:/";

    }

    @RequestMapping(value = "/registerCan", method = RequestMethod.POST)
    public String registerCandidatePost(@ModelAttribute("candidate") Candidate candidate, HttpServletRequest request) {
        candidate.setEnabled(true);
        candidateService.addCandidate(candidate);
        try {
            request.login(candidate.getUsername(), candidate.getPassword());
        } catch (ServletException e) {
            return "registerCustomerSuccess";
        }

        return "redirect:/";

    }

}
