package com.ejobfinder.controller;

import com.ejobfinder.drools.utils.DroolsUtility;
import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.facts.*;
import com.ejobfinder.utils.BooleanMapper;
import com.ejobfinder.utils.LanguageMapper;
import com.ejobfinder.utils.consts.*;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CandidateController {

    @Autowired
    private DroolsUtility droolsUtility;

    @Autowired
    private CandidateFacts candidateFacts;

    @RequestMapping("/candidate")
    public String candidatePage(Model model) {

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


        return "candidate";
    }

    @RequestMapping("/candidate/{jobId}")
    public String candidatePage(@PathVariable String jobId, Model model) {
        return "redirect:/jobOfferList/viewJobOffer/" + jobId;
    }

    @RequestMapping(value = "fact/technology", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTechnologyFact(@RequestParam String name, @RequestParam String level, @RequestParam String year) {

        double yearDouble = Double.valueOf(year);
        int lvl = Integer.valueOf(level);
        TechnologyFact fact = new TechnologyFact(name, yearDouble, lvl);

        candidateFacts.addTechnologyFact(fact);

        int newSize = candidateFacts.getTechnologyFacts().size();
        return new ResponseEntity<String>("Technology fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/skill", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSkillFact(@RequestParam String name, @RequestParam String level) {

        int lvl = Integer.valueOf(level);

        SkillFact fact = new SkillFact(name, lvl);

        candidateFacts.addSkillFact(fact);

        int newSize = candidateFacts.getSkillFacts().size();
        return new ResponseEntity<String>("Skill fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/tool", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addToolFact(@RequestParam String name, @RequestParam String level, @RequestParam String year) {

        int lvl = Integer.valueOf(level);
        double yearDouble = Double.valueOf(year);

        ToolFact fact = new ToolFact(name, yearDouble, lvl);

        candidateFacts.addToolFact(fact);

        int newSize = candidateFacts.getToolFacts().size();
        return new ResponseEntity<String>("Tool fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/language", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addLanguageFact(@RequestParam String name, @RequestParam String level) {


        int lvl = LanguageMapper.getLanguageLvlInt(level);

        LanguageFact fact = new LanguageFact(name, lvl);

        candidateFacts.addLanguageFact(fact);

        int newSize = candidateFacts.getLanguageFacts().size();
        return new ResponseEntity<String>("Language fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/location", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addLocationeFact(@RequestParam String name) {

        LocationFact fact = new LocationFact(name);

        candidateFacts.addLocationFact(fact);

        int newSize = candidateFacts.getLocationFacts().size();
        return new ResponseEntity<String>("Location fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/salary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSalaryeFact(@RequestParam String amountDown, @RequestParam String amountUp) {

        double amountDownDouble = Double.valueOf(amountDown);
        double amountUpDouble = Double.valueOf(amountUp);


        SalaryFact fact = new SalaryFact(amountUpDouble, amountDownDouble);

        candidateFacts.addSalaryFact(fact);

        int newSize = candidateFacts.getSalaryFacts().size();
        return new ResponseEntity<String>("Salary ule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/workingHours", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addWorkingHoursFact(@RequestParam String name) {

        WorkingHoursFact fact = new WorkingHoursFact(name);

        candidateFacts.addWorkingHoursFact(fact);

        int newSize = candidateFacts.getWorkingHoursFacts().size();
        return new ResponseEntity<String>("WorkingHours fact created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "fact/typeOfContract", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTypeOfContractFact(@RequestParam String name) {

        TypeOfContractFact fact = new TypeOfContractFact(name);

        candidateFacts.addTypeOfContractFact(fact);

        int newSize = candidateFacts.getTypeOfContractFacts().size();
        return new ResponseEntity<String>("TypeOfContract fact created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "fact/periodOfNotice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPeriodOfNoticeFact(@RequestParam String name) {

        PeriodOfNoticeFact fact = new PeriodOfNoticeFact(name);

        candidateFacts.addPeriodOfNoticeFact(fact);

        int newSize = candidateFacts.getPeriodOfNoticeFacts().size();
        return new ResponseEntity<String>("PeriodOfNotice fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/previousEmployerRule", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPreviousEmployerFact(@RequestParam String name, @RequestParam String year, @RequestParam String isStillWorkingParam, @RequestParam String haveProfessionalExperienceParam) {


        Boolean stillWorking = BooleanMapper.getBoolean(isStillWorkingParam);
        Boolean haveProfessionalExperienc = BooleanMapper.getBoolean(haveProfessionalExperienceParam);
        double yearDouble = Double.valueOf(year);

        PreviousEmployerFact fact = new PreviousEmployerFact(name, yearDouble, stillWorking, haveProfessionalExperienc);

        int newSize = candidateFacts.getPreviousEmployerFacts().size();
        return new ResponseEntity<String>("PreviousEmployerfact created with new size=" + newSize, HttpStatus.OK);
    }


    @RequestMapping(value = "fact/education", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addEducationFact(@RequestParam String professionalTitle, @RequestParam String fieldOfStudy, @RequestParam String modeOfStudy,
                                                   @RequestParam String isAbroadStudent, @RequestParam String isStudentParam) {

        Boolean studyAbroad = BooleanMapper.getBoolean(isAbroadStudent);
        Boolean isStudent = BooleanMapper.getBoolean(isStudentParam);

        EducationFact fact = new EducationFact(professionalTitle, fieldOfStudy, modeOfStudy, studyAbroad, isStudent);

        candidateFacts.addEducationFact(fact);
        int newSize = candidateFacts.getEducationFacts().size();
        return new ResponseEntity<String>("Education fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> deleteFact(@RequestParam String name) {
        candidateFacts.deleteFact(name);
        return new ResponseEntity<String>("rule deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "fact/finalize", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> finalizeAndUpdatedProfile() {
        CandidateFacts factsAboutUser = this.candidateFacts;
        Candidate candidate = new Candidate();
        StatelessKieSession session = droolsUtility.loadSession("3");

        session.setGlobal("candidate", candidate);

        session.execute(factsAboutUser.getTechnologyFacts());
        session.execute(factsAboutUser.getTypeOfContractFacts());
        session.execute(factsAboutUser.getEducationFacts());
        session.execute(factsAboutUser.getLanguageFacts());
        session.execute(factsAboutUser.getLocationFacts());
        session.execute(factsAboutUser.getPeriodOfNoticeFacts());
        session.execute(factsAboutUser.getPreviousEmployerFacts());
        session.execute(factsAboutUser.getSalaryFacts());
        session.execute(factsAboutUser.getToolFacts());
        session.execute(factsAboutUser.getWorkingHoursFacts());
        session.execute(factsAboutUser.getSkillFacts());

        System.out.println("Candidate SCORE = '" + candidate.getScore() + "' points");


        return new ResponseEntity<String>("profile successfully updated", HttpStatus.OK);
    }

}
