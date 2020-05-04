package com.ejobfinder.controller;

import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.JobOfferApplication;
import com.ejobfinder.model.facts.*;
import com.ejobfinder.service.CandidateService;
import com.ejobfinder.service.JobOfferService;
import com.ejobfinder.utils.BooleanMapper;
import com.ejobfinder.utils.LanguageMapper;
import com.ejobfinder.utils.consts.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Controller
public class CandidateController {

    private final CandidateFacts candidateFacts;
    private final JobOfferService jobOfferService;
    private final CandidateService candidateService;

    public CandidateController(CandidateFacts candidateFacts, JobOfferService jobOfferService, CandidateService candidateService) {
        this.candidateFacts = candidateFacts;
        this.jobOfferService = jobOfferService;
        this.candidateService = candidateService;
    }

    @RequestMapping("/candidate")
    public String candidatePage(Model model, @AuthenticationPrincipal User activeUser) {
        Candidate candidate = activeUser != null ? candidateService.getCandidateByUsername(activeUser.getUsername()) : null;

        //clear facts in session
        clearFactsInSession();
        //set readed facts from DB to session
        uploadFactsIntoSession(Objects.requireNonNull(candidate));
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

        updateModelWithStaticAttributes(model);
        model.addAttribute("isTemp", Boolean.FALSE);

        return "candidate";
    }

    @RequestMapping("/candidate/withNewProfile/{jobId}")
    public String candidatePageWIthNewProfile(Model model, @PathVariable String jobId,
                                              @AuthenticationPrincipal User activeUser) {

        updateModelWithStaticAttributes(model);
        model.addAttribute("isTemp", Boolean.TRUE);
        model.addAttribute("jobId", jobId);

        return "candidate";
    }

    @RequestMapping("/candidate/candidateMainPage")
    public String candidateMainPage(Model model, @AuthenticationPrincipal User activeUser) {

        Candidate candidate = activeUser != null ? candidateService.getCandidateByUsername(activeUser.getUsername()) : null;

        assert candidate != null;
        AtomicBoolean notificationNeeded = new AtomicBoolean(false);
        Set<JobOfferApplication> jobOfferApplications = jobOfferService.getAllJobOffers().stream()
                .map(JobOffer::getAllJobOfferApplications)
                .flatMap(Collection::stream)
                .filter(application -> application.getCandidate().getCandidateId() == candidate.getCandidateId())
                .collect(Collectors.toSet());

        jobOfferApplications.forEach(application -> {
            if (application.getNotify()) {
                notificationNeeded.set(true);
                application.setNotify(false);
            }
        });

        if (notificationNeeded.get()) {
            candidate.setJobOfferApplications(jobOfferApplications);
            candidateService.updateCandidate(candidate);
        }
        model.addAttribute("name", candidate.getName());
        model.addAttribute("lastName", candidate.getLastName());
        model.addAttribute("candidateId", candidate.getCandidateId());
        model.addAttribute("applications", candidate.getJobOfferApplications());
        model.addAttribute("propositions", candidate.getProposals());

        Optional<JobOfferApplication> newProposal = candidate.getProposals().stream().filter(application -> !application.getCandidateAcceptancee()).findAny();

        model.addAttribute("newProposition", newProposal.isPresent());
        model.addAttribute("notificationNeeded", notificationNeeded.get());
        return "candidateMainPage";
    }

    @RequestMapping("/candidate/{jobId}")
    public String candidatePage(@PathVariable String jobId, Model model) {
        return "redirect:/jobOfferList/viewJobOffer/" + jobId;
    }

    @RequestMapping(value = "fact/technology", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTechnologyFact(@RequestParam String name,
                                                    @RequestParam String level,
                                                    @RequestParam String year) {

        double yearDouble = Double.parseDouble(year);
        int lvl = Integer.parseInt(level);
        TechnologyFact fact = new TechnologyFact(name, yearDouble, lvl);

        candidateFacts.addTechnologyFact(fact);

        int newSize = candidateFacts.getTechnologyFacts().size();
        return new ResponseEntity<>("Technology fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/skill", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSkillFact(@RequestParam String name, @RequestParam String level) {

        int lvl = Integer.parseInt(level);
        SkillFact fact = new SkillFact(name, lvl);

        candidateFacts.addSkillFact(fact);

        int newSize = candidateFacts.getSkillFacts().size();
        return new ResponseEntity<>("Skill fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/tool", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addToolFact(@RequestParam String name, @RequestParam String level,
                                              @RequestParam String year) {

        int lvl = Integer.parseInt(level);
        double yearDouble = Double.parseDouble(year);
        ToolFact fact = new ToolFact(name, yearDouble, lvl);

        candidateFacts.addToolFact(fact);

        int newSize = candidateFacts.getToolFacts().size();
        return new ResponseEntity<>("Tool fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/language", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addLanguageFact(@RequestParam String name, @RequestParam String level) {
        int lvl = LanguageMapper.getLanguageLvlInt(level);
        LanguageFact fact = new LanguageFact(name, lvl);

        candidateFacts.addLanguageFact(fact);

        int newSize = candidateFacts.getLanguageFacts().size();
        return new ResponseEntity<>("Language fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/location", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addLocationeFact(@RequestParam String name) {
        LocationFact fact = new LocationFact(name);
        candidateFacts.addLocationFact(fact);

        int newSize = candidateFacts.getLocationFacts().size();
        return new ResponseEntity<>("Location fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/salary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSalaryeFact(@RequestParam String amountDown, @RequestParam String amountUp) {

        double amountDownDouble = Double.parseDouble(amountDown);
        double amountUpDouble = Double.parseDouble(amountUp);
        SalaryFact fact = new SalaryFact(amountUpDouble, amountDownDouble);

        candidateFacts.addSalaryFact(fact);

        int newSize = candidateFacts.getSalaryFacts().size();
        return new ResponseEntity<>("Salary ule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/workingHours", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addWorkingHoursFact(@RequestParam String name) {
        WorkingHoursFact fact = new WorkingHoursFact(name);

        candidateFacts.addWorkingHoursFact(fact);

        int newSize = candidateFacts.getWorkingHoursFacts().size();
        return new ResponseEntity<>("WorkingHours fact created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "fact/typeOfContract", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTypeOfContractFact(@RequestParam String name) {
        TypeOfContractFact fact = new TypeOfContractFact(name);

        candidateFacts.addTypeOfContractFact(fact);

        int newSize = candidateFacts.getTypeOfContractFacts().size();
        return new ResponseEntity<>("TypeOfContract fact created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "fact/periodOfNotice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPeriodOfNoticeFact(@RequestParam String name) {
        PeriodOfNoticeFact fact = new PeriodOfNoticeFact(name);

        candidateFacts.addPeriodOfNoticeFact(fact);

        int newSize = candidateFacts.getPeriodOfNoticeFacts().size();
        return new ResponseEntity<>("PeriodOfNotice fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/previousEmployerRule", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPreviousEmployerFact(@RequestParam String name, @RequestParam String year,
                                                          @RequestParam String isStillWorkingParam,
                                                          @RequestParam String haveProfessionalExperienceParam) {
        Boolean stillWorking = BooleanMapper.getBoolean(isStillWorkingParam);
        Boolean haveProfessionalExperienc = BooleanMapper.getBoolean(haveProfessionalExperienceParam);
        double yearDouble = Double.parseDouble(year);

        PreviousEmployerFact fact = new PreviousEmployerFact(name, yearDouble, stillWorking, haveProfessionalExperienc);
        candidateFacts.addPreviousEmployerFact(fact);
        int newSize = candidateFacts.getPreviousEmployerFacts().size();
        return new ResponseEntity<>("PreviousEmployerfact created with new size=" + newSize, HttpStatus.OK);
    }


    @RequestMapping(value = "fact/education", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addEducationFact(@RequestParam String professionalTitle, @RequestParam String fieldOfStudy,
                                                   @RequestParam String modeOfStudy, @RequestParam String isAbroadStudent,
                                                   @RequestParam String isStudentParam) {
        Boolean studyAbroad = BooleanMapper.getBoolean(isAbroadStudent);
        Boolean isStudent = BooleanMapper.getBoolean(isStudentParam);

        EducationFact fact = new EducationFact(professionalTitle, fieldOfStudy, modeOfStudy, studyAbroad, isStudent);

        candidateFacts.addEducationFact(fact);
        int newSize = candidateFacts.getEducationFacts().size();
        return new ResponseEntity<>("Education fact created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "fact/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> deleteFact(@RequestParam String name) {
        candidateFacts.deleteFact(name);
        return new ResponseEntity<>("fact deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "fact/finalize", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> finalizeAndUpdatedProfile(@AuthenticationPrincipal User activeUser) {
        CandidateFacts factsAboutUser = this.candidateFacts;
        Candidate candidate = activeUser != null ? candidateService.getCandidateByUsername(activeUser.getUsername()) : null;
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

                    jobOfferService.matchTagsWithCandidateCV(jobOffer, finalCandidate, newApplication);

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

        return new ResponseEntity<>("profile successfully updated", HttpStatus.OK);
    }

    @RequestMapping(value = "fact/applyWithTempProfile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> applyWithTempProfile(@AuthenticationPrincipal User activeUser, @RequestParam String jobId) {
        CandidateFacts factsAboutUser = this.candidateFacts;
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobId);
        Candidate candidate = activeUser != null ? candidateService.getCandidateByUsername(activeUser.getUsername()) : null;
        if (candidate == null) {
            candidate = new Candidate();
        }
        copyValuesToCandidate(candidate, factsAboutUser);
        JobOfferApplication application = new JobOfferApplication();

        application.setJobOffer(jobOffer);
        application.setCandidate(candidate);
        application.setCandidateAcceptancee(Boolean.TRUE);

        Integer score = candidateService.evaluateScoringOnJobOffer(jobId, candidate);
        Double percent = (double) ((score / jobOffer.getMaximalPoints()) * 100);

        jobOfferService.matchTagsWithCandidateCV(jobOffer, candidate, application);

        application.setPercentOfMaxScore(percent);
        application.setCalculatedScore(score);

        candidate.getJobOfferApplications().add(application);
        jobOffer.addApplication(application);

        jobOfferService.updateJobOffer(jobOffer);

        return new ResponseEntity<>("Applied", HttpStatus.OK);
    }

    @RequestMapping(value = "/candidate/uploadCV", method = RequestMethod.POST)
    public String uploadCV(@AuthenticationPrincipal User activeUser, @RequestParam("file") MultipartFile file) {

        Candidate candidate = activeUser != null ? candidateService.getCandidateByUsername(activeUser.getUsername()) : null;
        try {
            assert candidate != null;
            candidate.setCvFIle(file.getBytes());
        } catch (IOException ignored) {

        }
        candidateService.updateCandidate(candidate);
        List<JobOffer> allJobOffers = jobOfferService.getAllJobOffers();

        updateAllExistingApplications(candidate, allJobOffers);

        return "candidateMainPage";
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

        return "redirect:/";
    }

    @RequestMapping("/candidate/acceptByCandidate")
    public ResponseEntity<String> jobOfferAccerptByCandidate(@RequestParam String jobID,
                                                             @RequestParam Integer candidateID,
                                                             @RequestParam Boolean isAccepted) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(jobID);
        Optional<JobOfferApplication> application = jobOffer.getAllJobOfferApplications().stream().filter(application1 -> candidateID == application1.getCandidate().getCandidateId()).findFirst();

        if (application.isPresent()) {
            application.get().setCandidateAcceptancee(isAccepted);
            if (application.get().getPotential() && application.get().getEmployerAcceptancee()) {
                application.get().setPotential(false);
            }
            if (!isAccepted && application.get().getEmployerAcceptancee() != null && !application.get().getEmployerAcceptancee()) {
                Set<JobOfferApplication> copy = jobOffer.getAllJobOfferApplications();
                jobOffer.removeApplication(application.get());

            }
            jobOfferService.updateJobOffer(jobOffer);
        }
        return new ResponseEntity<>("JobOffer updated", HttpStatus.OK);
    }

    private void uploadFactsIntoSession(Candidate candidate) {
        assert candidate != null;
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
    }

    private void clearFactsInSession() {
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

    private void updateAllExistingApplications(Candidate candidate, List<JobOffer> allJobOffers) {
        allJobOffers.forEach(jobOffer -> {
            AtomicBoolean shouldUpdate = new AtomicBoolean(false);
            jobOffer.getAllJobOfferApplications().forEach(application -> {
                if (application.getCandidate().getCandidateId() == candidate.getCandidateId()) {
                    jobOfferService.matchTagsWithCandidateCV(jobOffer, candidate, application);
                    shouldUpdate.set(true);
                }
            });
            if (shouldUpdate.get()) {
                jobOfferService.updateJobOffer(jobOffer);
            }
        });
    }

    private void updateModelWithStaticAttributes(Model model) {
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
    }

}
