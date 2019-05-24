package com.ejobfinder.controller;

import com.ejobfinder.drools.Condition.Operator;
import com.ejobfinder.drools.Rule;
import com.ejobfinder.drools.utils.DroolsUtility;
import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.TechnologyFact;
import com.ejobfinder.model.rules.*;
import com.ejobfinder.service.TechnologyRulesService;
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
import java.util.List;

@Controller
public class RuleController {

    @Autowired
    private TechnologyRulesService technologyRulesService;

    @Autowired
    private DroolsUtility droolsUtility;

    @Autowired
    PerfectEmployeeRules perfectEmployeeRules;

    @RequestMapping("/employer/jobOfferInventory/perfectEmployeeRules/{jobId}")
    public String rule(Model model, @PathVariable("jobId") String jobId, @AuthenticationPrincipal User activeUser) throws Exception {
        TechnologyRule technologyRule1 = new TechnologyRule("Java", Operator.GREATER_THAN_OR_EQUAL_TO, 2.0, 3, Operator.GREATER_THAN_OR_EQUAL_TO, 2);
        TechnologyRule technologyRule2 = new TechnologyRule("Spring", Operator.GREATER_THAN_OR_EQUAL_TO, 1.0, 2, Operator.GREATER_THAN_OR_EQUAL_TO, 3);
        TechnologyRule technologyRule3 = new TechnologyRule("SQL", Operator.GREATER_THAN_OR_EQUAL_TO, 3.0, 3, Operator.GREATER_THAN_OR_EQUAL_TO, 1);
        List<TechnologyRule> technologyRuleList = Arrays.asList(technologyRule1, technologyRule2, technologyRule3);

        List<Rule> rules = technologyRulesService.createRulesForTechnology(technologyRuleList);

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
        candidate.setTechnologyFacts(technologyFactList);

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

        double yearDouble = StringUtils.isNumber(year) ? Double.valueOf(year) : 0;
        int scr = Integer.valueOf(score);
        int lvl = Integer.valueOf(level);

        TechnologyRule rule = new TechnologyRule(name, OperatorConverter.convertTextOperatorToSymbolicOperator(yearOperator), yearDouble, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);

        Integer newSize = addTechnologyRule(rule).size();
        return new ResponseEntity<String>("Technology rule created with new size=" + newSize, HttpStatus.OK);


    }

    @RequestMapping(value = "rule/skill", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSkillRule(@RequestParam String name, @RequestParam String level, @RequestParam String levelOperator, @RequestParam String score) {

        int scr = Integer.valueOf(score);
        int lvl = Integer.valueOf(level);

        SkillRule rule = new SkillRule(name, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);

        Integer newSize = addSkillRule(rule).size();

        return new ResponseEntity<String>("Skill rule created with new size=" + newSize, HttpStatus.OK);


    }

    @RequestMapping(value = "rule/tool", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addToolyRule(@RequestParam String name, @RequestParam String level, @RequestParam String levelOperator, @RequestParam String score, @RequestParam String year, @RequestParam String yearOperator) {

        int scr = Integer.valueOf(score);
        int lvl = Integer.valueOf(level);
        double yearDouble = StringUtils.isNumber(year) ? Double.valueOf(year) : 0;
        ToolRule rule = new ToolRule(name, OperatorConverter.convertTextOperatorToSymbolicOperator(yearOperator), yearDouble, lvl, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);

        Integer newSize = addToolRule(rule).size();

        return new ResponseEntity<String>("Tool rule created with new size=" + newSize, HttpStatus.OK);


    }

    @RequestMapping(value = "rule/language", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addLanguageRule(@RequestParam String name, @RequestParam String level, @RequestParam String levelOperator, @RequestParam String score) {

        int scr = Integer.valueOf(score);
        LanguageRule rule = new LanguageRule(name, level, OperatorConverter.convertTextOperatorToSymbolicOperator(levelOperator), scr);

        Integer newSize = addLanguageRule(rule).size();

        return new ResponseEntity<String>("Language rule created with new size=" + newSize, HttpStatus.OK);


    }

    @RequestMapping(value = "rule/location", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addLocationeRule(@RequestParam String name, @RequestParam String score) {

        int scr = Integer.valueOf(score);
        LocationRule locationRule = new LocationRule(name, scr);

        Integer newSize = addLocationRule(locationRule).size();

        return new ResponseEntity<String>("Location rule created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "rule/salary", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSalaryeRule(@RequestParam String amountDown, @RequestParam String amountDownOperator,
                                                 @RequestParam String amountUp, @RequestParam String amountUpOperator,
                                                 @RequestParam String score) {

        int scr = Integer.valueOf(score);
        double amountDownDouble = StringUtils.isNumber(amountDown) ? Double.valueOf(amountDown) : 0;
        double amountUpDouble = StringUtils.isNumber(amountUp) ? Double.valueOf(amountUp) : 0;

        SalaryRule rule = new SalaryRule(amountDownDouble, OperatorConverter.convertTextOperatorToSymbolicOperator(amountDownOperator),
                amountUpDouble, OperatorConverter.convertTextOperatorToSymbolicOperator(amountUpOperator), scr);

        Integer newSize = addSalaryRule(rule).size();
        return new ResponseEntity<String>("Salary ule created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "rule/workingHours", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addWorkingHoursRule(@RequestParam String name, @RequestParam String operator, @RequestParam String score) {

        int scr = Integer.valueOf(score);
        Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);

        WorkingHoursRule rule = new WorkingHoursRule(name, op, scr);

        Integer newSize = addWorkingHoursRule(rule).size();

        return new ResponseEntity<String>("WorkingHours rule created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "rule/typeOfContract", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTypeOfContractRule(@RequestParam String name, @RequestParam String operator, @RequestParam String score) {

        int scr = Integer.valueOf(score);
        Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);

        TypeOfContractRule rule = new TypeOfContractRule(name, op, scr);

        Integer newSize = addTypeOfContractRule(rule).size();

        return new ResponseEntity<String>("WorkingHours rule created with new size=" + newSize, HttpStatus.OK);

    }

    @RequestMapping(value = "rule/periodOfNotice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPeriodOfNoticeRule(@RequestParam String name, @RequestParam String operator, @RequestParam String score) {

        int scr = Integer.valueOf(score);
        Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);

        PeriodOfNoticeRule rule = new PeriodOfNoticeRule(name, op, scr);

        Integer newSize = addPeriodOfNoticeRule(rule).size();

        return new ResponseEntity<String>("WorkingHours rule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/previousEmployerRule", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPreviousEmployerRule(@RequestParam String name, @RequestParam String year, @RequestParam String operator, @RequestParam String isStillWorkingParam, @RequestParam String haveProfessionalExperienceParam, @RequestParam String score) {

        int scr = Integer.valueOf(score);
        Operator op = OperatorConverter.convertTextOperatorToSymbolicOperator(operator);
        double yearDouble = StringUtils.isNumber(year) ? Double.valueOf(year) : 0;
        Boolean stillWorking = Boolean.valueOf(isStillWorkingParam);
        Boolean haveProfessionalExperienc = Boolean.valueOf(haveProfessionalExperienceParam);

        PreviousEmployerRule rule = new PreviousEmployerRule(name, op, yearDouble, stillWorking, haveProfessionalExperienc, scr);

        Integer newSize = addPreviousEmployerRule(rule).size();

        return new ResponseEntity<String>("PreviousEmployerRule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/education", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addEducationRule(@RequestParam String professionalTitle, @RequestParam String fieldOfStudy, @RequestParam String modeOfStudy,
                                                   @RequestParam String isAbroadStudent, @RequestParam String isStudentParam, @RequestParam String score) {

        int scr = Integer.valueOf(score);
        Boolean studyAbroad = Boolean.valueOf(isAbroadStudent);
        Boolean isStudent = Boolean.valueOf(isStudentParam);

        EducationRule rule = new EducationRule(professionalTitle, fieldOfStudy, modeOfStudy, studyAbroad, isStudent, scr);

        Integer newSize = addEducationRule(rule).size();

        return new ResponseEntity<String>("PreviousEmployerRule created with new size=" + newSize, HttpStatus.OK);
    }

    @RequestMapping(value = "rule/finalize", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> finalizeAndCreateFile() {

        PerfectEmployeeRules perfectEmployeeDescription = this.perfectEmployeeRules;


        return new ResponseEntity<String>("File created", HttpStatus.OK);


    }

    @RequestMapping(value = "rule/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> finalizeAndCreateFile(@RequestParam String name) {

        ResponseEntity<String> resp = new ResponseEntity<String>("rule deleted", HttpStatus.OK);

        Integer idx = getIndexFromName(name);
        if (idx != null) {
            int index = idx - 1;
            if (name.contains("_tech_")) {
                perfectEmployeeRules.getTechnologyRules().remove(index);
                return resp;
            }
            if (name.contains("_skill_")) {
                perfectEmployeeRules.getSkillRules().remove(index);
                return resp;
            }

            if (name.contains("_tool_")) {
                perfectEmployeeRules.getToolRules().remove(index);
                return resp;
            }

            if (name.contains("_lang_")) {
                perfectEmployeeRules.getLanguageRules().remove(index);
                return resp;
            }

            if (name.contains("_loc_")) {
                perfectEmployeeRules.getLocationRules().remove(index);
                return resp;
            }

            if (name.contains("_workH_")) {
                perfectEmployeeRules.getWorkingHoursRules().remove(index);
                return resp;
            }

            if (name.contains("_contr_")) {
                perfectEmployeeRules.getTypeOfContractRules().remove(index);
                return resp;
            }

            if (name.contains("_period_")) {
                perfectEmployeeRules.getPeriodOfNoticeRules().remove(index);
                return resp;
            }

            if (name.contains("_edu_")) {
                perfectEmployeeRules.getEducationRules().remove(index);
                return resp;
            }

            if (name.contains("_prev_")) {
                perfectEmployeeRules.getPreviousEmployerRules().remove(index);
                return resp;
            }

            if (name.contains("_salary_")) {
                perfectEmployeeRules.getSalaryRules().remove(index);
                return resp;
            }

        }

        return resp;


    }

    private Integer getIndexFromName(String name) {
        if (name != null) {
            String[] split = name.split("_");
            if (split != null) {
                String last = split[split.length - 1];
                if (StringUtils.isNumber(last)) {
                    return Integer.valueOf(last);
                }
            }
        }
        return null;
    }

    private List<TechnologyRule> addTechnologyRule(TechnologyRule rule) {
        perfectEmployeeRules.getTechnologyRules().add(rule);
        return perfectEmployeeRules.getTechnologyRules();
    }

    private List<SkillRule> addSkillRule(SkillRule rule) {
        perfectEmployeeRules.getSkillRules().add(rule);
        return perfectEmployeeRules.getSkillRules();
    }

    private List<ToolRule> addToolRule(ToolRule rule) {
        perfectEmployeeRules.getToolRules().add(rule);
        return perfectEmployeeRules.getToolRules();
    }

    private List<LanguageRule> addLanguageRule(LanguageRule rule) {
        perfectEmployeeRules.getLanguageRules().add(rule);
        return perfectEmployeeRules.getLanguageRules();
    }

    private List<LocationRule> addLocationRule(LocationRule rule) {
        perfectEmployeeRules.getLocationRules().add(rule);
        return perfectEmployeeRules.getLocationRules();
    }

    private List<EducationRule> addEducationRule(EducationRule rule) {
        perfectEmployeeRules.getEducationRules().add(rule);
        return perfectEmployeeRules.getEducationRules();
    }

    private List<SalaryRule> addSalaryRule(SalaryRule rule) {
        perfectEmployeeRules.getSalaryRules().add(rule);
        return perfectEmployeeRules.getSalaryRules();
    }

    private List<PeriodOfNoticeRule> addPeriodOfNoticeRule(PeriodOfNoticeRule rule) {
        perfectEmployeeRules.getPeriodOfNoticeRules().add(rule);
        return perfectEmployeeRules.getPeriodOfNoticeRules();
    }

    private List<PreviousEmployerRule> addPreviousEmployerRule(PreviousEmployerRule rule) {
        perfectEmployeeRules.getPreviousEmployerRules().add(rule);
        return perfectEmployeeRules.getPreviousEmployerRules();
    }

    private List<TypeOfContractRule> addTypeOfContractRule(TypeOfContractRule rule) {
        perfectEmployeeRules.getTypeOfContractRules().add(rule);
        return perfectEmployeeRules.getTypeOfContractRules();
    }

    private List<WorkingHoursRule> addWorkingHoursRule(WorkingHoursRule rule) {
        perfectEmployeeRules.getWorkingHoursRules().add(rule);
        return perfectEmployeeRules.getWorkingHoursRules();
    }
}

