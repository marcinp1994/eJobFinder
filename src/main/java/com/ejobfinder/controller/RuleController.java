package com.ejobfinder.controller;

import com.ejobfinder.drools.Condition.Operator;
import com.ejobfinder.drools.Rule;
import com.ejobfinder.drools.utils.DroolsUtility;
import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.Employer;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.JobOfferApplication;
import com.ejobfinder.model.rules.*;
import com.ejobfinder.service.CandidateService;
import com.ejobfinder.service.EmployerService;
import com.ejobfinder.service.JobOfferService;
import com.ejobfinder.service.impl.RulesServiceImpl;
import com.ejobfinder.utils.BooleanMapper;
import com.ejobfinder.utils.LanguageMapper;
import com.ejobfinder.utils.OperatorConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class RuleController {

    private final DroolsUtility droolsUtility;
    private final EmployerService employerService;
    private final CandidateService candidateService;
    private final PerfectEmployeeRules perfectEmployeeRules;
    private final RulesServiceImpl rulesService;
    private final JobOfferService jobOfferService;

    public RuleController(DroolsUtility droolsUtility, EmployerService employerService, CandidateService candidateService, PerfectEmployeeRules perfectEmployeeRules, RulesServiceImpl rulesService, JobOfferService jobOfferService) {
        this.droolsUtility = droolsUtility;
        this.employerService = employerService;
        this.candidateService = candidateService;
        this.perfectEmployeeRules = perfectEmployeeRules;
        this.rulesService = rulesService;
        this.jobOfferService = jobOfferService;
    }

    @RequestMapping(value = "rule/technology", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTechnologyRule(@RequestParam String name, @RequestParam String level,
                                                    @RequestParam String year, @RequestParam String yearOperator,
                                                    @RequestParam String levelOperator, @RequestParam String score) {
        double yearDouble = Double.parseDouble(year);
        int scr = Integer.parseInt(score);
        int lvl = Integer.parseInt(level);
        TechnologyRule rule = new TechnologyRule(name, OperatorConverter.convertTextOperatorToSymbolicOperator(yearOperator), yearDouble, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);
        int newSize = rulesService.addTechnologyRule(rule).size();
        return new ResponseEntity<>("Technology rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/skill", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSkillRule(@RequestParam String name, @RequestParam String level,
                                               @RequestParam String levelOperator, @RequestParam String score) {
        int scr = Integer.parseInt(score);
        int lvl = Integer.parseInt(level);
        SkillRule rule = new SkillRule(name, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);
        int newSize = rulesService.addSkillRule(rule).size();
        return new ResponseEntity<>("Skill rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/tool", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addToolyRule(@RequestParam String name, @RequestParam String level,
                                               @RequestParam String levelOperator, @RequestParam String score,
                                               @RequestParam String year, @RequestParam String yearOperator) {
        int scr = Integer.parseInt(score);
        int lvl = Integer.parseInt(level);
        double yearDouble = Double.parseDouble(year);
        ToolRule rule = new ToolRule(name, OperatorConverter.convertTextOperatorToSymbolicOperator(yearOperator), yearDouble, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);
        int newSize = rulesService.addToolRule(rule).size();
        return new ResponseEntity<>("Tool rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/language", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addLanguageRule(@RequestParam String name, @RequestParam String level,
                                                  @RequestParam String levelOperator, @RequestParam String score) {

        int scr = Integer.parseInt(score);
        int lvl = LanguageMapper.getLanguageLvlInt(level);
        LanguageRule rule = new LanguageRule(name, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);
        int newSize = rulesService.addLanguageRule(rule).size();
        return new ResponseEntity<>("Language rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/location", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addLocationeRule(@RequestParam String name, @RequestParam String score) {

        int scr = Integer.parseInt(score);
        LocationRule locationRule = new LocationRule(name, scr);
        int newSize = rulesService.addLocationRule(locationRule).size();
        return new ResponseEntity<>("Location rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/salary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSalaryeRule(@RequestParam String amountDown, @RequestParam String amountUp,
                                                 @RequestParam String score) {
        int scr = Integer.parseInt(score);
        double amountDownDouble = Double.parseDouble(amountDown);
        double amountUpDouble = Double.parseDouble(amountUp);
        SalaryRule rule = new SalaryRule(amountDownDouble, Operator.EQUAL_TO,
                amountUpDouble, Operator.EQUAL_TO, scr);
        int newSize = rulesService.addSalaryRule(rule).size();
        return new ResponseEntity<>("Salary ule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/workingHours", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addWorkingHoursRule(@RequestParam String name, @RequestParam String operator,
                                                      @RequestParam String score) {
        int scr = Integer.parseInt(score);
        Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);
        WorkingHoursRule rule = new WorkingHoursRule(name, op, scr);
        int newSize = rulesService.addWorkingHoursRule(rule).size();
        return new ResponseEntity<>("WorkingHours rule created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "rule/typeOfContract", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTypeOfContractRule(@RequestParam String name, @RequestParam String operator,
                                                        @RequestParam String score) {
        int scr = Integer.parseInt(score);
        Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);
        TypeOfContractRule rule = new TypeOfContractRule(name, op, scr);
        int newSize = rulesService.addTypeOfContractRule(rule).size();
        return new ResponseEntity<>("TypeOfContract rule created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "rule/periodOfNotice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPeriodOfNoticeRule(@RequestParam String name, @RequestParam String operator,
                                                        @RequestParam String score) {
        int scr = Integer.parseInt(score);
        Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);
        PeriodOfNoticeRule rule = new PeriodOfNoticeRule(name, op, scr);
        int newSize = rulesService.addPeriodOfNoticeRule(rule).size();
        return new ResponseEntity<>("PeriodOfNotice rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/previousEmployerRule", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPreviousEmployerRule(@RequestParam String name, @RequestParam String year,
                                                          @RequestParam String operator, @RequestParam String isStillWorkingParam,
                                                          @RequestParam String haveProfessionalExperienceParam, @RequestParam String score) {
        int scr = Integer.parseInt(score);
        Boolean stillWorking = BooleanMapper.getBoolean(isStillWorkingParam);
        Boolean haveProfessionalExperienc = BooleanMapper.getBoolean(haveProfessionalExperienceParam);
        PreviousEmployerRule rule = new PreviousEmployerRule();

        rule.setScore(scr);
        rule.setStillWorking(stillWorking);
        rule.setHaveProfessionalExperience(haveProfessionalExperienc);

        if (!StringUtils.isEmpty(year)) {
            Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);
            double yearDouble = Double.parseDouble(year);

            rule.setYearOperator(op);
            rule.setYear(yearDouble);
        }
        if (!StringUtils.isEmpty(name)) {
            rule.setJobTitle(name);
        }

        int newSize = rulesService.addPreviousEmployerRule(rule).size();
        return new ResponseEntity<>("PreviousEmployerRule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/education", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addEducationRule(@RequestParam String professionalTitle, @RequestParam String fieldOfStudy,
                                                   @RequestParam String modeOfStudy, @RequestParam String isAbroadStudent,
                                                   @RequestParam String isStudentParam, @RequestParam String score) {
        int scr = Integer.parseInt(score);
        Boolean studyAbroad = BooleanMapper.getBoolean(isAbroadStudent);
        Boolean isStudent = BooleanMapper.getBoolean(isStudentParam);
        EducationRule rule = new EducationRule();

        rule.setScore(scr);
        rule.setStudyAbroad(studyAbroad);
        rule.setStudent(isStudent);

        if (!StringUtils.isEmpty(professionalTitle)) {
            rule.setProfessionalTitle(professionalTitle);
        }

        if (!StringUtils.isEmpty(fieldOfStudy)) {
            rule.setFieldOfStudy(fieldOfStudy);
        }

        if (!StringUtils.isEmpty(modeOfStudy)) {
            rule.setModeOfStudy(modeOfStudy);
        }

        int newSize = rulesService.addEducationRule(rule).size();
        return new ResponseEntity<>("Education rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/finalize", method = RequestMethod.POST)
    public String finalizeAndCreateFile(@AuthenticationPrincipal User activeUser, Model model, @RequestParam String threshold) throws Exception {
        Employer employer = employerService.getEmployerByUsername(activeUser.getUsername());

        model.addAttribute("isPremium", employer.getPremiumMember());
        Integer thresholdPercentage = Integer.valueOf(threshold);
        PerfectEmployeeRules perfectEmployeeRules = this.perfectEmployeeRules;
        List<Rule> technologyRules = rulesService.createRulesForTechnology(perfectEmployeeRules.getTechnologyRules());
        List<Rule> skillRules = rulesService.createRulesForSkills(perfectEmployeeRules.getSkillRules());
        List<Rule> workingHoursRules = rulesService.createRulesForWorkingHours(perfectEmployeeRules.getWorkingHoursRules());
        List<Rule> educationRules = rulesService.createRulesForEducation(perfectEmployeeRules.getEducationRules());
        List<Rule> languageRules = rulesService.createRulesForLanguage(perfectEmployeeRules.getLanguageRules());
        List<Rule> periodOfNoticeRules = rulesService.createRulesForPeriodOfNotice(perfectEmployeeRules.getPeriodOfNoticeRules());
        List<Rule> LocationRules = rulesService.createRulesForLocation(perfectEmployeeRules.getLocationRules());
        List<Rule> previousEmployerRules = rulesService.createRulesForPreviousEmployee(perfectEmployeeRules.getPreviousEmployerRules());
        List<Rule> salaryRules = rulesService.createRulesForSalary(perfectEmployeeRules.getSalaryRules());
        List<Rule> toolRules = rulesService.createRulesForTool(perfectEmployeeRules.getToolRules());
        List<Rule> typeOfContractRules = rulesService.createRulesForTypeOfContract(perfectEmployeeRules.getTypeOfContractRules());
        List<Rule> perfectEmployeeRuleList = Stream.of(technologyRules, skillRules, workingHoursRules, educationRules, languageRules, periodOfNoticeRules, LocationRules, previousEmployerRules, salaryRules, toolRules, typeOfContractRules)
                .flatMap(Collection::stream).collect(Collectors.toList());

        droolsUtility.createRules(perfectEmployeeRuleList, "rules/template/PerfectEmployeeRules.drl", perfectEmployeeRules.getJobId());

        JobOffer offer = jobOfferService.getJobOfferById(perfectEmployeeRules.getJobId());
        int maxPointsValue = perfectEmployeeRuleList.stream().mapToInt(Rule::getScore).sum();

        offer.setContainsRules(Boolean.TRUE);
        offer.setThresholdPercentagePoints(thresholdPercentage);
        offer.setMaximalPoints(maxPointsValue);

        addTagsFromRulesAndOffer(perfectEmployeeRules, offer);

        if (employer.getPremiumMember()) {
            createPotentialApplications(offer);
        }

        jobOfferService.updateJobOffer(offer);
        model.addAttribute("potentialApplications", offer.getPotentialJobOfferApplications());
        return "redirect:/employer/jobOfferInventory";
    }

    private void addTagsFromRulesAndOffer(PerfectEmployeeRules perfectEmployeeRules, JobOffer offer) {
        Set<String> tags = offer.getUniqueTags();
        String tagString = offer.getTags();
        if (!perfectEmployeeRules.getTechnologyRules().isEmpty()) {
            perfectEmployeeRules.getTechnologyRules().forEach(rule -> {
                if (!StringUtils.containsIgnoreCase(tagString, rule.getName())) {
                    tags.add(rule.getName());
                }
            });
        }
        if (!perfectEmployeeRules.getToolRules().isEmpty()) {
            perfectEmployeeRules.getToolRules().forEach(rule -> {
                if (!StringUtils.containsIgnoreCase(tagString, rule.getName())) {
                    tags.add(rule.getName());
                }
            });
        }
        if (!perfectEmployeeRules.getLocationRules().isEmpty()) {
            perfectEmployeeRules.getLocationRules().forEach(rule -> {
                if (!StringUtils.containsIgnoreCase(tagString, rule.getName())) {
                    tags.add(rule.getName());
                }
            });
        }
        if (!perfectEmployeeRules.getLanguageRules().isEmpty()) {
            perfectEmployeeRules.getLanguageRules().forEach(rule -> {
                if (!StringUtils.containsIgnoreCase(tagString, rule.getName())) {
                    tags.add(rule.getName());
                }
            });
        }

        tags.add(offer.getEmployer().getCompanyName());
        tags.add(offer.getLocation().getCity());
        tags.add(offer.getLocation().getCountry());
        tags.add(offer.getPosition());
        tags.add(offer.getCategory());

        offer.setTags(tags);
    }

    private void createPotentialApplications(JobOffer offer) {
        List<Candidate> candidates = candidateService.getAllCandidates();
        candidates.forEach(candidate -> {
            JobOfferApplication application = new JobOfferApplication();

            application.setJobOffer(offer);
            application.setCandidate(candidate);

            Integer score = candidateService.evaluateScoringOnJobOffer(offer.getJobId(), candidate);
            Double percent = (double) (score * 100 / offer.getMaximalPoints());
            jobOfferService.matchTagsWithCandidateCV(offer, candidate, application);
            application.setPercentOfMaxScore(percent);
            application.setCalculatedScore(score);
            application.setPotential(true);


            offer.addApplication(application);
        });
    }

    @RequestMapping(value = "rule/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> deleteRule(@RequestParam String name) {
        rulesService.deleteRule(name);
        return new ResponseEntity<>("rule deleted", HttpStatus.OK);
    }
}

