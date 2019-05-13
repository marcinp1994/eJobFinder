package com.ejobfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CandidateController {

    @RequestMapping("/candidate")
    public String candidatePage(Model model) {
        return "candidate";
    }

}
