package com.ejobfinder.service.impl;

import com.ejobfinder.drools.Condition;
import com.ejobfinder.drools.Rule;
import com.ejobfinder.model.facts.*;
import com.ejobfinder.model.rules.*;
import org.h2.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RulesServiceImpl {

    private PerfectEmployeeRules perfectEmployeeRules;

    public RulesServiceImpl(PerfectEmployeeRules perfectEmployeeRules) {
        this.perfectEmployeeRules = perfectEmployeeRules;
    }

    public List<Rule> createRulesForSkills(List<SkillRule> skillRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (SkillRule skillRule : skillRuleList) {
            Rule rule = createRule("Skill Rule", SkillFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            createCondition("name", Condition.Operator.EQUAL_TO, skillRule.getName(), conditionList);
            if (skillRule.getLevel() != 0) {
                createCondition("level", skillRule.getLevelOperator(), skillRule.getLevel(), conditionList);
            }
            rules.add(updateRule(skillRule.getScore(), rule, conditionList));

        }
        return rules;
    }

    public List<Rule> createRulesForTechnology(List<TechnologyRule> technologyRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (TechnologyRule technologyRule : technologyRuleList) {
            Rule rule = createRule("Technology Rule", TechnologyFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            createCondition("name", Condition.Operator.EQUAL_TO, technologyRule.getName(), conditionList);
            if (technologyRule.getLevel() != 0) {
                createCondition("level", technologyRule.getLevelOperator(), technologyRule.getLevel(), conditionList);
            }
            if (technologyRule.getYear() != 0) {
                createCondition("year", technologyRule.getYearOperator(), technologyRule.getYear(), conditionList);
            }
            rules.add(updateRule(technologyRule.getScore(), rule, conditionList));
        }
        return rules;
    }

    public List<Rule> createRulesForWorkingHours(List<WorkingHoursRule> workingHoursRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (WorkingHoursRule workingHoursRule : workingHoursRuleList) {
            if (workingHoursRule.getWorkingHours() != null) {
                Rule rule = createRule("Working Hours Rule", WorkingHoursFact.class.getName());
                List<Condition> conditionList = new ArrayList<>();
                createCondition("workingHours", workingHoursRule.getWorkingHoursOperator(), workingHoursRule.getWorkingHours(), conditionList);
                rules.add(updateRule(workingHoursRule.getScore(), rule, conditionList));
            }
        }
        return rules;
    }

    public List<Rule> createRulesForEducation(List<EducationRule> educationRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (EducationRule educationRule : educationRuleList) {
            Rule rule = createRule("Education Rule", EducationFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            if (educationRule.getFieldOfStudy() != null) {
                createCondition("fieldOfStudy", Condition.Operator.EQUAL_TO, educationRule.getFieldOfStudy(), conditionList);
            }
            if (educationRule.getModeOfStudy() != null) {
                createCondition("modeOfStudy", Condition.Operator.EQUAL_TO, educationRule.getModeOfStudy(), conditionList);
            }
            if (educationRule.getProfessionalTitle() != null) {
                createCondition("professionalTitle", Condition.Operator.EQUAL_TO, educationRule.getProfessionalTitle(), conditionList);
            }
            if (educationRule.isStudent() != null) {
                createCondition("student", Condition.Operator.EQUAL_TO, educationRule.isStudent(), conditionList);
            }
            if (educationRule.isStudyAbroad() != null) {
                createCondition("studyAbroad", Condition.Operator.EQUAL_TO, educationRule.isStudyAbroad(), conditionList);
            }
            rules.add(updateRule(educationRule.getScore(), rule, conditionList));
        }
        return rules;
    }

    public List<Rule> createRulesForLanguage(List<LanguageRule> languageRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (LanguageRule languageRule : languageRuleList) {
            Rule rule = createRule("Language Rule", LanguageFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            createCondition("name", Condition.Operator.EQUAL_TO, languageRule.getName(), conditionList);
            if (languageRule.getLevel() != 0) {
                createCondition("level", languageRule.getLevelOperator(), languageRule.getLevel(), conditionList);
            }
            rules.add(updateRule(languageRule.getScore(), rule, conditionList));
        }
        return rules;
    }

    public List<Rule> createRulesForLocation(List<LocationRule> locationRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (LocationRule locationRule : locationRuleList) {
            if (locationRule.getName() != null) {
                Rule rule = createRule("Location Rule", LocationFact.class.getName());
                List<Condition> conditionList = new ArrayList<>();
                createCondition("name", Condition.Operator.EQUAL_TO, locationRule.getName(), conditionList);
                rules.add(updateRule(locationRule.getScore(), rule, conditionList));
            }
        }
        return rules;
    }

    public List<Rule> createRulesForPeriodOfNotice(List<PeriodOfNoticeRule> periodOfNoticeRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (PeriodOfNoticeRule periodOfNoticeRule : periodOfNoticeRuleList) {
            if (periodOfNoticeRule.getPeriodOfNotice() != null) {
                Rule rule = createRule("Period of Notice Rule", PeriodOfNoticeFact.class.getName());
                List<Condition> conditionList = new ArrayList<>();
                createCondition("periodOfNotice", periodOfNoticeRule.getPeriodOfNoticeOperator(), periodOfNoticeRule.getPeriodOfNotice(), conditionList);
                rules.add(updateRule(periodOfNoticeRule.getScore(), rule, conditionList));
            }
        }
        return rules;
    }

    public List<Rule> createRulesForPreviousEmployee(List<PreviousEmployerRule> previousEmployerRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (PreviousEmployerRule previousEmployerRule : previousEmployerRuleList) {
            Rule rule = createRule("Previous Employer Rule", PreviousEmployerFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            if (previousEmployerRule.getJobTitle() != null) {
                createCondition("jobTitle", Condition.Operator.EQUAL_TO, previousEmployerRule.getJobTitle(), conditionList);
            }

            if (previousEmployerRule.isStillWorking() != null) {
                createCondition("stillWorking", Condition.Operator.EQUAL_TO, previousEmployerRule.isStillWorking(), conditionList);
            }
            if (previousEmployerRule.isHaveProfessionalExperience() != null) {
                createCondition("haveProfessionalExperience", Condition.Operator.EQUAL_TO, previousEmployerRule.isHaveProfessionalExperience(), conditionList);
            }
            if (previousEmployerRule.getYear() != 0) {
                createCondition("year", previousEmployerRule.getYearOperator(), previousEmployerRule.getYear(), conditionList);
            }
            rules.add(updateRule(previousEmployerRule.getScore(), rule, conditionList));
        }
        return rules;
    }

    public List<Rule> createRulesForSalary(List<SalaryRule> salaryRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (SalaryRule salaryRule : salaryRuleList) {
            Rule rule = createRule("Salary Rule", SalaryFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            if (salaryRule.getAmountDown() != 0) {
                createCondition("amountDown", salaryRule.getAmountDownOperator(), salaryRule.getAmountDown(), conditionList);
            }
            if (salaryRule.getAmountUp() != 0) {
                createCondition("amountUp", salaryRule.getAmountUpOperator(), salaryRule.getAmountUp(), conditionList);
            }
            rules.add(updateRule(salaryRule.getScore(), rule, conditionList));
        }
        return rules;
    }

    public List<Rule> createRulesForTool(List<ToolRule> toolRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (ToolRule toolRule : toolRuleList) {
            Rule rule = createRule("Tool Rule", ToolFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            createCondition("name", Condition.Operator.EQUAL_TO, toolRule.getName(), conditionList);
            if (toolRule.getLevel() != 0) {
                createCondition("level", toolRule.getLevelOperator(), toolRule.getLevel(), conditionList);
            }
            if (toolRule.getYear() != 0) {
                createCondition("year", toolRule.getYearOperator(), toolRule.getYear(), conditionList);
            }
            rules.add(updateRule(toolRule.getScore(), rule, conditionList));
        }
        return rules;
    }

    public List<Rule> createRulesForTypeOfContract(List<TypeOfContractRule> typeOfContractRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (TypeOfContractRule typeOfContractRule : typeOfContractRuleList) {
            if (typeOfContractRule.getTypeOfContract() != null) {
                Rule rule = createRule("Type Of Contract Rule", TypeOfContractFact.class.getName());
                List<Condition> conditionList = new ArrayList<>();
                createCondition("typeOfContract", typeOfContractRule.getTypeOfContractOperator(), typeOfContractRule.getTypeOfContract(), conditionList);
                rules.add(updateRule(typeOfContractRule.getScore(), rule, conditionList));
            }
        }
        return rules;
    }

    private Rule updateRule(int score, Rule rule, List<Condition> conditionList) {
        rule.setConditions(conditionList);
        rule.setAction(String.valueOf(score));
        rule.setScore(score);
        return rule;
    }


    public List<TechnologyRule> addTechnologyRule(TechnologyRule rule) {
        perfectEmployeeRules.getTechnologyRules().add(rule);
        return perfectEmployeeRules.getTechnologyRules();
    }

    public List<SkillRule> addSkillRule(SkillRule rule) {
        perfectEmployeeRules.getSkillRules().add(rule);
        return perfectEmployeeRules.getSkillRules();
    }

    public List<ToolRule> addToolRule(ToolRule rule) {
        perfectEmployeeRules.getToolRules().add(rule);
        return perfectEmployeeRules.getToolRules();
    }

    public List<LanguageRule> addLanguageRule(LanguageRule rule) {
        perfectEmployeeRules.getLanguageRules().add(rule);
        return perfectEmployeeRules.getLanguageRules();
    }

    public List<LocationRule> addLocationRule(LocationRule rule) {
        perfectEmployeeRules.getLocationRules().add(rule);
        return perfectEmployeeRules.getLocationRules();
    }

    public List<EducationRule> addEducationRule(EducationRule rule) {
        perfectEmployeeRules.getEducationRules().add(rule);
        return perfectEmployeeRules.getEducationRules();
    }

    public List<SalaryRule> addSalaryRule(SalaryRule rule) {
        perfectEmployeeRules.getSalaryRules().add(rule);
        return perfectEmployeeRules.getSalaryRules();
    }

    public List<PeriodOfNoticeRule> addPeriodOfNoticeRule(PeriodOfNoticeRule rule) {
        perfectEmployeeRules.getPeriodOfNoticeRules().add(rule);
        return perfectEmployeeRules.getPeriodOfNoticeRules();
    }

    public List<PreviousEmployerRule> addPreviousEmployerRule(PreviousEmployerRule rule) {
        perfectEmployeeRules.getPreviousEmployerRules().add(rule);
        return perfectEmployeeRules.getPreviousEmployerRules();
    }

    public List<TypeOfContractRule> addTypeOfContractRule(TypeOfContractRule rule) {
        perfectEmployeeRules.getTypeOfContractRules().add(rule);
        return perfectEmployeeRules.getTypeOfContractRules();
    }

    public List<WorkingHoursRule> addWorkingHoursRule(WorkingHoursRule rule) {
        perfectEmployeeRules.getWorkingHoursRules().add(rule);
        return perfectEmployeeRules.getWorkingHoursRules();
    }

    public void deleteRule(String name) {
        Integer idx = getIndexFromName(name);
        if (idx != null) {
            int index = idx - 1;
            if (name.contains("_tech_")) {
                perfectEmployeeRules.getTechnologyRules().remove(index);
                return;
            }
            if (name.contains("_skill_")) {
                perfectEmployeeRules.getSkillRules().remove(index);
                return;
            }
            if (name.contains("_tool_")) {
                perfectEmployeeRules.getToolRules().remove(index);
                return;
            }
            if (name.contains("_lang_")) {
                perfectEmployeeRules.getLanguageRules().remove(index);
                return;
            }
            if (name.contains("_loc_")) {
                perfectEmployeeRules.getLocationRules().remove(index);
                return;
            }
            if (name.contains("_workH_")) {
                perfectEmployeeRules.getWorkingHoursRules().remove(index);
                return;
            }
            if (name.contains("_contr_")) {
                perfectEmployeeRules.getTypeOfContractRules().remove(index);
                return;
            }
            if (name.contains("_period_")) {
                perfectEmployeeRules.getPeriodOfNoticeRules().remove(index);
                return;
            }
            if (name.contains("_edu_")) {
                perfectEmployeeRules.getEducationRules().remove(index);
                return;
            }
            if (name.contains("_prev_")) {
                perfectEmployeeRules.getPreviousEmployerRules().remove(index);
                return;
            }
            if (name.contains("_salary_")) {
                perfectEmployeeRules.getSalaryRules().remove(index);
            }
        }
    }

    private Integer getIndexFromName(String name) {
        if (name != null) {
            String[] split = name.split("_");
            String last = split[split.length - 1];
            if (StringUtils.isNumber(last)) {
                return Integer.valueOf(last);
            }
        }
        return null;
    }

    private Rule createRule(String ruleName, String factNameClass){
        Rule rule = new Rule(ruleName);
        rule.setDataObject(factNameClass);
        return rule;
    }

    private void createCondition(String property, Condition.Operator operator, Object value, List<Condition> conditionList){
        Condition condition = new Condition();
        condition.setProperty(property);
        condition.setOperator(operator);
        condition.setValue(value);
        conditionList.add(condition);
    }

}
