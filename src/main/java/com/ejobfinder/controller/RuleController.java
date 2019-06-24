package com.ejobfinder.controller;

import com.ejobfinder.drools.Condition.Operator;
import com.ejobfinder.drools.Rule;
import com.ejobfinder.drools.utils.DroolsUtility;
import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.facts.TechnologyFact;
import com.ejobfinder.model.rules.*;
import com.ejobfinder.service.JobOfferService;
import com.ejobfinder.service.RulesService;
import com.ejobfinder.utils.BooleanMapper;
import com.ejobfinder.utils.LanguageMapper;
import com.ejobfinder.utils.OperatorConverter;
import org.h2.util.StringUtils;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class RuleController {

    @Autowired
    private DroolsUtility droolsUtility;

    @Autowired
    private PerfectEmployeeRules perfectEmployeeRules;

    @Autowired
    private RulesService rulesService;

    @Autowired
    private JobOfferService jobOfferService;


    @RequestMapping("/employer/jobOfferInventory/perfectEmployeeRules/{jobId}")
    public String rule(Model model, @PathVariable("jobId") String jobId, @AuthenticationPrincipal User activeUser) throws Exception {
        TechnologyRule technologyRule1 = new TechnologyRule("Java", Operator.GREATER_THAN_OR_EQUAL_TO, 2.0, 3, Operator.GREATER_THAN_OR_EQUAL_TO, 2);
        TechnologyRule technologyRule2 = new TechnologyRule("Spring", Operator.GREATER_THAN_OR_EQUAL_TO, 1.0, 2, Operator.GREATER_THAN_OR_EQUAL_TO, 3);
        TechnologyRule technologyRule3 = new TechnologyRule("SQL", Operator.GREATER_THAN_OR_EQUAL_TO, 3.0, 3, Operator.GREATER_THAN_OR_EQUAL_TO, 1);
        List<TechnologyRule> technologyRuleList = Arrays.asList(technologyRule1, technologyRule2, technologyRule3);

        List<Rule> rules = rulesService.createRulesForTechnology(technologyRuleList);

        droolsUtility.createRules(rules, "rules/template/PerfectEmployeeRules.drl", jobId);

        return "candidate";
    }

    @RequestMapping("/fireRule/{jobId}")
    public String fireRule(Model model, @PathVariable("jobId") String jobId, @AuthenticationPrincipal User activeUser) {
        StatelessKieSession session = droolsUtility.loadSession(jobId);

        Candidate candidate = new Candidate();
        TechnologyFact technologyFact = new TechnologyFact("Java", 4, 5);
        TechnologyFact technologyFact2 = new TechnologyFact("Spring", 1, 5);
        TechnologyFact technologyFact3 = new TechnologyFact("SQL", 1, 5);
        List<TechnologyFact> technologyFactList = Arrays.asList(technologyFact, technologyFact2, technologyFact3);

        System.out.println("Technology name = '" + technologyFact.getName() + "' and level = '" + technologyFact.getLevel() + "' and years = '" + technologyFact.getYear() + "'.");
        System.out.println("Technology name = '" + technologyFact2.getName() + "' and level = '" + technologyFact2.getLevel() + "' and years = '" + technologyFact2.getYear() + "'.");
        System.out.println("Technology name = '" + technologyFact3.getName() + "' and level = '" + technologyFact3.getLevel() + "' and years = '" + technologyFact3.getYear() + "'.");

        session.setGlobal("candidate", candidate);
        session.execute(technologyFactList);
        System.out.println("Candidate SCORE = '" + candidate.getScore() + "' points");

        return "candidate";
    }


    @RequestMapping(value = "rule/technology", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTechnologyRule(@RequestParam String name, @RequestParam String level, @RequestParam String year, @RequestParam String yearOperator,
                                                    @RequestParam String levelOperator, @RequestParam String score) {
        double yearDouble = Double.valueOf(year);
        int scr = Integer.valueOf(score);
        int lvl = Integer.valueOf(level);
        TechnologyRule rule = new TechnologyRule(name, OperatorConverter.convertTextOperatorToSymbolicOperator(yearOperator), yearDouble, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);
        Integer newSize = rulesService.addTechnologyRule(rule).size();
        return new ResponseEntity<String>("Technology rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/skill", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSkillRule(@RequestParam String name, @RequestParam String level, @RequestParam String levelOperator, @RequestParam String score) {
        int scr = Integer.valueOf(score);
        int lvl = Integer.valueOf(level);
        SkillRule rule = new SkillRule(name, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);
        Integer newSize = rulesService.addSkillRule(rule).size();
        return new ResponseEntity<String>("Skill rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/tool", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addToolyRule(@RequestParam String name, @RequestParam String level, @RequestParam String levelOperator, @RequestParam String score, @RequestParam String year, @RequestParam String yearOperator) {
        int scr = Integer.valueOf(score);
        int lvl = Integer.valueOf(level);
        double yearDouble = Double.valueOf(year);
        ToolRule rule = new ToolRule(name, OperatorConverter.convertTextOperatorToSymbolicOperator(yearOperator), yearDouble, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);
        Integer newSize = rulesService.addToolRule(rule).size();
        return new ResponseEntity<String>("Tool rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/language", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addLanguageRule(@RequestParam String name, @RequestParam String level, @RequestParam String levelOperator, @RequestParam String score) {

        int scr = Integer.valueOf(score);
        int lvl = LanguageMapper.getLanguageLvlInt(level);
        LanguageRule rule = new LanguageRule(name, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);
        Integer newSize = rulesService.addLanguageRule(rule).size();
        return new ResponseEntity<String>("Language rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/location", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addLocationeRule(@RequestParam String name, @RequestParam String score) {

        int scr = Integer.valueOf(score);
        LocationRule locationRule = new LocationRule(name, scr);
        Integer newSize = rulesService.addLocationRule(locationRule).size();
        return new ResponseEntity<String>("Location rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/salary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSalaryeRule(@RequestParam String amountDown, @RequestParam String amountUp,
                                                 @RequestParam String score) {
        int scr = Integer.valueOf(score);
        double amountDownDouble = Double.valueOf(amountDown);
        double amountUpDouble = Double.valueOf(amountUp);
        SalaryRule rule = new SalaryRule(amountDownDouble, Operator.EQUAL_TO,
                amountUpDouble, Operator.EQUAL_TO, scr);
        Integer newSize = rulesService.addSalaryRule(rule).size();
        return new ResponseEntity<String>("Salary ule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/workingHours", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addWorkingHoursRule(@RequestParam String name, @RequestParam String operator, @RequestParam String score) {
        int scr = Integer.valueOf(score);
        Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);
        WorkingHoursRule rule = new WorkingHoursRule(name, op, scr);
        Integer newSize = rulesService.addWorkingHoursRule(rule).size();
        return new ResponseEntity<String>("WorkingHours rule created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "rule/typeOfContract", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTypeOfContractRule(@RequestParam String name, @RequestParam String operator, @RequestParam String score) {
        int scr = Integer.valueOf(score);
        Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);
        TypeOfContractRule rule = new TypeOfContractRule(name, op, scr);
        Integer newSize = rulesService.addTypeOfContractRule(rule).size();
        return new ResponseEntity<String>("TypeOfContract rule created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "rule/periodOfNotice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPeriodOfNoticeRule(@RequestParam String name, @RequestParam String operator, @RequestParam String score) {
        int scr = Integer.valueOf(score);
        Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);
        PeriodOfNoticeRule rule = new PeriodOfNoticeRule(name, op, scr);
        Integer newSize = rulesService.addPeriodOfNoticeRule(rule).size();
        return new ResponseEntity<String>("PeriodOfNotice rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/previousEmployerRule", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPreviousEmployerRule(@RequestParam String name, @RequestParam String year, @RequestParam String operator, @RequestParam String isStillWorkingParam, @RequestParam String haveProfessionalExperienceParam, @RequestParam String score) {
        int scr = Integer.valueOf(score);

        Boolean stillWorking = BooleanMapper.getBoolean(isStillWorkingParam);
        Boolean haveProfessionalExperienc = BooleanMapper.getBoolean(haveProfessionalExperienceParam);

        PreviousEmployerRule rule = new PreviousEmployerRule();

        rule.setScore(scr);
        rule.setStillWorking(stillWorking);
        rule.setHaveProfessionalExperience(haveProfessionalExperienc);

        if (!StringUtils.isNullOrEmpty(year)) {
            Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);
            double yearDouble = Double.valueOf(year);

            rule.setYearOperator(op);
            rule.setYear(yearDouble);
        }
        if (!StringUtils.isNullOrEmpty(name)) {
            rule.setJobTitle(name);
        }

        Integer newSize = rulesService.addPreviousEmployerRule(rule).size();
        return new ResponseEntity<String>("PreviousEmployerRule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/education", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addEducationRule(@RequestParam String professionalTitle, @RequestParam String fieldOfStudy, @RequestParam String modeOfStudy,
                                                   @RequestParam String isAbroadStudent, @RequestParam String isStudentParam, @RequestParam String score) {
        int scr = Integer.valueOf(score);
        Boolean studyAbroad = BooleanMapper.getBoolean(isAbroadStudent);
        Boolean isStudent = BooleanMapper.getBoolean(isStudentParam);
        EducationRule rule = new EducationRule();

        rule.setScore(scr);
        rule.setStudyAbroad(studyAbroad);
        rule.setStudent(isStudent);

        if (!StringUtils.isNullOrEmpty(professionalTitle)) {
            rule.setProfessionalTitle(professionalTitle);
        }

        if (!StringUtils.isNullOrEmpty(fieldOfStudy)) {
            rule.setFieldOfStudy(fieldOfStudy);
        }

        if (!StringUtils.isNullOrEmpty(modeOfStudy)) {
            rule.setModeOfStudy(modeOfStudy);
        }

        Integer newSize = rulesService.addEducationRule(rule).size();
        return new ResponseEntity<String>("Education rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/finalize", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> finalizeAndCreateFile(@RequestParam String threshold) throws Exception {
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
        Integer maxPointsValue = perfectEmployeeRuleList.stream().mapToInt(rule -> rule.getScore()).sum();
        offer.setContainsRules(Boolean.TRUE);
        offer.setThresholdPercentagePoints(thresholdPercentage);
        offer.setMaximalPoints(maxPointsValue);
        jobOfferService.editJobOffer(offer);
        return new ResponseEntity<String>("rule finalize", HttpStatus.OK);
    }

    @RequestMapping(value = "rule/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> deleteRule(@RequestParam String name) {
        rulesService.deleteRule(name);
        return new ResponseEntity<String>("rule deleted", HttpStatus.OK);
    }
}

