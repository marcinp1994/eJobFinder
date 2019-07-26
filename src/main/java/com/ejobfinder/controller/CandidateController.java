package com.ejobfinder.controller;

import com.ejobfinder.drools.utils.DroolsUtility;
import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.JobOfferApplication;
import com.ejobfinder.model.facts.*;
import com.ejobfinder.service.CandidateService;
import com.ejobfinder.service.JobOfferService;
import com.ejobfinder.utils.BooleanMapper;
import com.ejobfinder.utils.LanguageMapper;
import com.ejobfinder.utils.consts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class CandidateController {

    @Autowired
    private DroolsUtility droolsUtility;

    @Autowired
    private CandidateFacts candidateFacts;


    @Autowired
    private JobOfferService jobOfferService;

    @Autowired
    private CandidateService candidateService;

    @RequestMapping("/candidate")
    public String candidatePage(Model model, @AuthenticationPrincipal User activeUser) {

        Candidate candidate = candidateService.getCandidateByUsername(activeUser.getUsername());

        //clear facts in session
        candidateFacts.getEducationFacts().clear();
        candidateFacts.getLanguageFacts().clear();
        candidateFacts.getSalaryFacts().clear();
        candidateFacts.getSkillFacts().clear();
        candidateFacts.getTechnologyFacts().clear();
        candidateFacts.getToolFacts().clear();
        candidateFacts.getPreviousEmployerFacts().clear();
        candidateFacts.getPeriodOfNoticeFacts().clear();
        candidateFacts.getTypeOfContractFacts().clear();
        candidateFacts.getWorkingHoursFacts().clear();

        //set readed facts from DB to session
        candidateFacts.getEducationFacts().addAll(candidate.getEducationFacts());
        candidateFacts.getLanguageFacts().addAll(candidate.getLanguageFacts());
        candidateFacts.getSalaryFacts().addAll(candidate.getSalaryFacts());
        candidateFacts.getSkillFacts().addAll(candidate.getSkillFacts());
        candidateFacts.getTechnologyFacts().addAll(candidate.getTechnologyFacts());
        candidateFacts.getToolFacts().addAll(candidate.getToolFacts());
        candidateFacts.getPreviousEmployerFacts().addAll(candidate.getPreviousEmployerFacts());
        candidateFacts.getPeriodOfNoticeFacts().addAll(candidate.getPeriodOfNoticeFacts());
        candidateFacts.getTypeOfContractFacts().addAll(candidate.getTypeOfContractFacts());
        candidateFacts.getWorkingHoursFacts().addAll(candidate.getWorkingHoursFacts());


        //add facts FROM DB to GUI
        model.addAttribute("technologyFacts", candidate.getTechnologyFacts());
        model.addAttribute("educationFacts", candidate.getEducationFacts());
        model.addAttribute("periodOfNoticeFacts", candidate.getPeriodOfNoticeFacts());
        model.addAttribute("languageFacts", candidate.getLanguageFacts());
        model.addAttribute("previousEmployerFacts", candidate.getPreviousEmployerFacts());
        model.addAttribute("salaryFacts", candidate.getSalaryFacts());
        model.addAttribute("skillFacts", candidate.getSkillFacts());
        model.addAttribute("typeOfContractFacts", candidate.getTypeOfContractFacts());
        model.addAttribute("locationFacts", candidate.getLocationFacts());
        model.addAttribute("workingHoursFacts", candidate.getWorkingHoursFacts());
        model.addAttribute("toolFacts", candidate.getToolFacts());

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
        model.addAttribute("isTemp", Boolean.FALSE);

        return "candidate";
    }

    @RequestMapping("/candidate/withNewProfile/{jobId}")
    public String candidatePageWIthNewProfile(Model model, @PathVariable String jobId, @AuthenticationPrincipal User activeUser) {

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
        model.addAttribute("isTemp", Boolean.TRUE);
        model.addAttribute("jobId", jobId);
        return "candidate";
    }

    @RequestMapping("/candidate/candidateMainPage")
    public String candidateMainPage(Model model, @AuthenticationPrincipal User activeUser) {

        Candidate candidate = candidateService.getCandidateByUsername(activeUser.getUsername());

        model.addAttribute("name", candidate.getName());
        model.addAttribute("lastName", candidate.getLastName());
        model.addAttribute("candidateId", candidate.getCandidateId());
        model.addAttribute("applications", candidate.getJobOfferApplications());
        model.addAttribute("propositions", candidate.getProposals());

        Optional<JobOfferApplication> newProposal = candidate.getProposals().stream().filter(application -> !application.getCandidateAcceptancee()).findAny();
        model.addAttribute("newProposition", newProposal.isPresent());
        return "candidateMainPage";
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
        candidateFacts.addPreviousEmployerFact(fact);
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
        return new ResponseEntity<String>("fact deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "fact/finalize", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> finalizeAndUpdatedProfile(@AuthenticationPrincipal User activeUser) {
        CandidateFacts factsAboutUser = this.candidateFacts;
        Candidate candidate = candidateService.getCandidateByUsername(activeUser.getUsername());
        if (candidate == null) {
            candidate = new Candidate();
            copyValuesToCandidate(candidate, factsAboutUser);
            candidateService.addCandidate(candidate);
        } else {
            copyValuesToCandidate(candidate, factsAboutUser);
            candidateService.updateCandidate(candidate);
        }
        // premium options update
        List<JobOffer> allJobOffers = jobOfferService.getAllJobOffers();
        Candidate finalCandidate = candidate;
        AtomicBoolean alreadyApply = new AtomicBoolean(false);
        allJobOffers.forEach(jobOffer -> {
            alreadyApply.set(false);
            jobOffer.getAllJobOfferApplications().forEach(application -> {
                //user already applied for job and now his result should be updated
                if (application.getCandidate().getCandidateId() == finalCandidate.getCandidateId()) {
                    Integer newScore = candidateService.evaluateScoringOnJobOffer(jobOffer.getJobId(), finalCandidate);
                    application.setCalculatedScore(newScore);
                    application.setPercentOfMaxScore((double) (newScore * 100 / jobOffer.getMaximalPoints()));
                    alreadyApply.set(true);
                }
            });
            //user havn't applied for this job
            if (!alreadyApply.get()) {
                if (jobOffer.getEmployer().getPremiumMember()) {
                    JobOfferApplication newApplication = new JobOfferApplication();
                    newApplication.setJobOffer(jobOffer);
                    newApplication.setCandidate(finalCandidate);
                    Integer score = candidateService.evaluateScoringOnJobOffer(jobOffer.getJobId(), finalCandidate);
                    Double percent = (double) (score * 100 / jobOffer.getMaximalPoints());
                    newApplication.setPercentOfMaxScore(percent);
                    newApplication.setCalculatedScore(score);
                    newApplication.setPotential(true);
                    jobOffer.addApplication(newApplication);
                    alreadyApply.set(true);
                }
            }
            if (alreadyApply.get()) {
                jobOfferService.updateJobOffer(jobOffer);
            }

        });


        return new ResponseEntity<String>("profile successfully updated", HttpStatus.OK);
    }

    @RequestMapping(value = "fact/applyWithTempProfile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> applyWithTempProfile(@AuthenticationPrincipal User activeUser, @RequestParam String jobId) {
        CandidateFacts factsAboutUser = this.candidateFacts;
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);
        Candidate candidate = candidateService.getCandidateByUsername(activeUser.getUsername());
        if (candidate == null) {
            candidate = new Candidate();
            copyValuesToCandidate(candidate, factsAboutUser);
        } else {
            copyValuesToCandidate(candidate, factsAboutUser);
        }
        JobOfferApplication application = new JobOfferApplication();

        application.setJobOffer(jobOffer);
        application.setCandidate(candidate);
        application.setCandidateAcceptancee(Boolean.TRUE);

        Integer score = candidateService.evaluateScoringOnJobOffer(jobId, candidate);
        Double percent = Double.valueOf((score / jobOffer.getMaximalPoints()) * 100);

        application.setPercentOfMaxScore(percent);
        application.setCalculatedScore(score);

        candidate.getJobOfferApplications().add(application);
        jobOffer.addApplication(application);

        jobOfferService.updateJobOffer(jobOffer);
        // candidateService.updateCandidate(candidate);

        return new ResponseEntity<String>("Applied", HttpStatus.OK);
    }

    private void copyValuesToCandidate(Candidate candidate, CandidateFacts factsAboutUser) {
        candidate.getEducationFacts().clear();
        candidate.getLanguageFacts().clear();
        candidate.getLocationFacts().clear();
        candidate.getSalaryFacts().clear();
        candidate.getSkillFacts().clear();
        candidate.getTechnologyFacts().clear();
        candidate.getToolFacts().clear();
        candidate.getPreviousEmployerFacts().clear();
        candidate.getPeriodOfNoticeFacts().clear();
        candidate.getTypeOfContractFacts().clear();
        candidate.getWorkingHoursFacts().clear();

        factsAboutUser.getEducationFacts().forEach(fact -> fact.setCandidate(candidate));
        factsAboutUser.getLanguageFacts().forEach(fact -> fact.setCandidate(candidate));
        factsAboutUser.getSalaryFacts().forEach(fact -> fact.setCandidate(candidate));
        factsAboutUser.getSkillFacts().forEach(fact -> fact.setCandidate(candidate));
        factsAboutUser.getTechnologyFacts().forEach(fact -> fact.setCandidate(candidate));
        factsAboutUser.getToolFacts().forEach(fact -> fact.setCandidate(candidate));
        factsAboutUser.getPreviousEmployerFacts().forEach(fact -> fact.setCandidate(candidate));
        factsAboutUser.getPeriodOfNoticeFacts().forEach(fact -> fact.setCandidate(candidate));
        factsAboutUser.getTypeOfContractFacts().forEach(fact -> fact.setCandidate(candidate));
        factsAboutUser.getWorkingHoursFacts().forEach(fact -> fact.setCandidate(candidate));
        factsAboutUser.getLocationFacts().forEach(fact -> fact.setCandidate(candidate));

        candidate.getEducationFacts().addAll(factsAboutUser.getEducationFacts());
        candidate.getLanguageFacts().addAll(factsAboutUser.getLanguageFacts());
        candidate.getSalaryFacts().addAll(factsAboutUser.getSalaryFacts());
        candidate.getSkillFacts().addAll(factsAboutUser.getSkillFacts());
        candidate.getTechnologyFacts().addAll(factsAboutUser.getTechnologyFacts());
        candidate.getToolFacts().addAll((factsAboutUser.getToolFacts()));
        candidate.getPreviousEmployerFacts().addAll(factsAboutUser.getPreviousEmployerFacts());
        candidate.getPeriodOfNoticeFacts().addAll(factsAboutUser.getPeriodOfNoticeFacts());
        candidate.getTypeOfContractFacts().addAll(factsAboutUser.getTypeOfContractFacts());
        candidate.getWorkingHoursFacts().addAll(factsAboutUser.getWorkingHoursFacts());
        candidate.getLocationFacts().addAll(factsAboutUser.getLocationFacts());
    }

}
