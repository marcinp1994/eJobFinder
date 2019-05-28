package com.ejobfinder.service.impl;

import com.ejobfinder.drools.Condition;
import com.ejobfinder.drools.Rule;
import com.ejobfinder.model.facts.*;
import com.ejobfinder.model.rules.*;
import com.ejobfinder.service.RulesService;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RulesServiceImpl implements RulesService {

    @Autowired
    private PerfectEmployeeRules perfectEmployeeRules;

    @Override
    public List<Rule> createRulesForSkills(List<SkillRule> skillRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (SkillRule skillRule : skillRuleList) {
            Rule rule = new Rule("Skill Rule");
            rule.setDataObject(SkillFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            Condition condition = new Condition();
            condition.setProperty("name");
            condition.setOperator(Condition.Operator.EQUAL_TO);
            condition.setValue(skillRule.getName());
            conditionList.add(condition);
            if (skillRule.getLevel() != 0) {
                Condition condition2 = new Condition();
                condition2.setProperty("level");
                condition2.setOperator(skillRule.getLevelOperator());
                condition2.setValue(skillRule.getLevel());
                conditionList.add(condition2);
            }
            rule.setConditions(conditionList);
            rule.setAction(String.valueOf(skillRule.getScore()));
            rules.add(rule);
        }
        return rules;
    }

    @Override
    public List<Rule> createRulesForTechnology(List<TechnologyRule> technologyRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (TechnologyRule technologyRule : technologyRuleList) {
            Rule rule = new Rule("Technology Rule");
            rule.setDataObject(TechnologyFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            Condition condition = new Condition();
            condition.setProperty("name");
            condition.setOperator(Condition.Operator.EQUAL_TO);
            condition.setValue(technologyRule.getName());
            conditionList.add(condition);
            if (technologyRule.getLevel() != 0) {
                Condition condition2 = new Condition();
                condition2.setProperty("level");
                condition2.setOperator(technologyRule.getLevelOperator());
                condition2.setValue(technologyRule.getLevel());
                conditionList.add(condition2);
            }
            if (technologyRule.getYear() != 0) {
                Condition condition3 = new Condition();
                condition3.setProperty("year");
                condition3.setOperator(technologyRule.getYearOperator());
                condition3.setValue(technologyRule.getYear());
                conditionList.add(condition3);
            }
            rule.setConditions(conditionList);
            rule.setAction(String.valueOf(technologyRule.getScore()));
            rules.add(rule);
        }
        return rules;
    }

    @Override
    public List<Rule> createRulesForWorkingHours(List<WorkingHoursRule> workingHoursRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (WorkingHoursRule workingHoursRule : workingHoursRuleList) {
            if (workingHoursRule.getWorkingHours() != null) {
                Rule rule = new Rule("Working Hours Rule");
                rule.setDataObject(WorkingHoursFact.class.getName());
                List<Condition> conditionList = new ArrayList<>();
                Condition condition = new Condition();
                condition.setProperty("workingHours");
                condition.setOperator(workingHoursRule.getWorkingHoursOperator());
                condition.setValue(workingHoursRule.getWorkingHours());
                conditionList.add(condition);
                rule.setConditions(conditionList);
                rule.setAction(String.valueOf(workingHoursRule.getScore()));
                rules.add(rule);
            }
        }
        return rules;
    }

    @Override
    public List<Rule> createRulesForEducation(List<EducationRule> educationRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (EducationRule educationRule : educationRuleList) {
            Rule rule = new Rule("Education Rule");
            rule.setDataObject(EducationFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            if (educationRule.getFieldOfStudy() != null) {
                Condition condition1 = new Condition();
                condition1.setProperty("fieldOfStudy");
                condition1.setOperator(Condition.Operator.EQUAL_TO);
                condition1.setValue(educationRule.getFieldOfStudy());
                conditionList.add(condition1);
            }
            if (educationRule.getModeOfStudy() != null) {
                Condition condition2 = new Condition();
                condition2.setProperty("modeOfStudy");
                condition2.setOperator(Condition.Operator.EQUAL_TO);
                condition2.setValue(educationRule.getModeOfStudy());
                conditionList.add(condition2);
            }

            if (educationRule.getProfessionalTitle() != null) {
                Condition condition3 = new Condition();
                condition3.setProperty("professionalTitle");
                condition3.setOperator(Condition.Operator.EQUAL_TO);
                condition3.setValue(educationRule.getProfessionalTitle());
                conditionList.add(condition3);
            }

            if (educationRule.isStudent() != null) {
                Condition condition4 = new Condition();
                condition4.setProperty("student");
                condition4.setOperator(Condition.Operator.EQUAL_TO);
                condition4.setValue(educationRule.isStudent());
                conditionList.add(condition4);
            }

            if (educationRule.isStudyAbroad() != null) {
                Condition condition5 = new Condition();
                condition5.setProperty("studyAbroad");
                condition5.setOperator(Condition.Operator.EQUAL_TO);
                condition5.setValue(educationRule.isStudyAbroad());
                conditionList.add(condition5);
            }

            rule.setConditions(conditionList);
            rule.setAction(String.valueOf(educationRule.getScore()));
            rules.add(rule);
        }
        return rules;
    }

    @Override
    public List<Rule> createRulesForLanguage(List<LanguageRule> languageRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (LanguageRule languageRule : languageRuleList) {
            Rule rule = new Rule("Language Rule");
            rule.setDataObject(LanguageFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            Condition condition = new Condition();
            condition.setProperty("name");
            condition.setOperator(Condition.Operator.EQUAL_TO);
            condition.setValue(languageRule.getName());
            conditionList.add(condition);
            if (languageRule.getLevel() != 0) {
                Condition condition2 = new Condition();
                condition2.setProperty("level");
                condition2.setOperator(languageRule.getLevelOperator());
                condition2.setValue(languageRule.getLevel());
                conditionList.add(condition2);
            }
            rule.setConditions(conditionList);
            rule.setAction(String.valueOf(languageRule.getScore()));
            rules.add(rule);
        }
        return rules;
    }

    @Override
    public List<Rule> createRulesForLocation(List<LocationRule> locationRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (LocationRule locationRule : locationRuleList) {
            if (locationRule.getName() != null) {
                Rule rule = new Rule("Location Rule");
                rule.setDataObject(LocationFact.class.getName());
                List<Condition> conditionList = new ArrayList<>();
                Condition condition = new Condition();
                condition.setProperty("name");
                condition.setOperator(Condition.Operator.EQUAL_TO);
                condition.setValue(locationRule.getName());
                conditionList.add(condition);
                rule.setConditions(conditionList);
                rule.setAction(String.valueOf(locationRule.getScore()));
                rules.add(rule);
            }
        }
        return rules;
    }

    @Override
    public List<Rule> createRulesForPeriodOfNotice(List<PeriodOfNoticeRule> periodOfNoticeRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (PeriodOfNoticeRule periodOfNoticeRule : periodOfNoticeRuleList) {
            if (periodOfNoticeRule.getPeriodOfNotice() != null) {
                Rule rule = new Rule("Period of Notice Rule");
                rule.setDataObject(PeriodOfNoticeFact.class.getName());
                List<Condition> conditionList = new ArrayList<>();
                Condition condition = new Condition();
                condition.setProperty("periodOfNotice");
                condition.setOperator(periodOfNoticeRule.getPeriodOfNoticeOperator());
                condition.setValue(periodOfNoticeRule.getPeriodOfNotice());
                conditionList.add(condition);
                rule.setConditions(conditionList);
                rule.setAction(String.valueOf(periodOfNoticeRule.getScore()));
                rules.add(rule);
            }
        }
        return rules;
    }

    @Override
    public List<Rule> createRulesForPreviousEmployee(List<PreviousEmployerRule> previousEmployerRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (PreviousEmployerRule previousEmployerRule : previousEmployerRuleList) {
            Rule rule = new Rule("Previous Employer Rule");
            rule.setDataObject(PreviousEmployerFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            if (previousEmployerRule.getJobTitle() != null) {
                Condition condition = new Condition();
                condition.setProperty("jobTitle");
                condition.setOperator(Condition.Operator.EQUAL_TO);
                condition.setValue(previousEmployerRule.getJobTitle());
                conditionList.add(condition);
            }

            if (previousEmployerRule.isStillWorking() != null) {
                Condition condition2 = new Condition();
                condition2.setProperty("stillWorking");
                condition2.setOperator(Condition.Operator.EQUAL_TO);
                condition2.setValue(previousEmployerRule.isStillWorking());
                conditionList.add(condition2);
            }
            if (previousEmployerRule.isHaveProfessionalExperience() != null) {
                Condition condition3 = new Condition();
                condition3.setProperty("haveProfessionalExperience");
                condition3.setOperator(Condition.Operator.EQUAL_TO);
                condition3.setValue(previousEmployerRule.isHaveProfessionalExperience());
                conditionList.add(condition3);
            }
            if (previousEmployerRule.getYear() != 0) {
                Condition condition4 = new Condition();
                condition4.setProperty("year");
                condition4.setOperator(previousEmployerRule.getYearOperator());
                condition4.setValue(previousEmployerRule.getYear());
                conditionList.add(condition4);
            }
            rule.setConditions(conditionList);
            rule.setAction(String.valueOf(previousEmployerRule.getScore()));
            rules.add(rule);
        }
        return rules;
    }

    @Override
    public List<Rule> createRulesForSalary(List<SalaryRule> salaryRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (SalaryRule salaryRule : salaryRuleList) {
            Rule rule = new Rule("Salary Rule");
            rule.setDataObject(SalaryFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            if (salaryRule.getAmountDown() != 0) {
                Condition condition2 = new Condition();
                condition2.setProperty("amountDown");
                condition2.setOperator(salaryRule.getAmountDownOperator());
                condition2.setValue(salaryRule.getAmountDown());
                conditionList.add(condition2);
            }
            if (salaryRule.getAmountUp() != 0) {
                Condition condition3 = new Condition();
                condition3.setProperty("amountUp");
                condition3.setOperator(salaryRule.getAmountUpOperator());
                condition3.setValue(salaryRule.getAmountUp());
                conditionList.add(condition3);
            }
            rule.setConditions(conditionList);
            rule.setAction(String.valueOf(salaryRule.getScore()));
            rules.add(rule);
        }
        return rules;
    }

    @Override
    public List<Rule> createRulesForTool(List<ToolRule> toolRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (ToolRule toolRule : toolRuleList) {
            Rule rule = new Rule("Tool Rule");
            rule.setDataObject(ToolFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            Condition condition = new Condition();
            condition.setProperty("name");
            condition.setOperator(Condition.Operator.EQUAL_TO);
            condition.setValue(toolRule.getName());
            conditionList.add(condition);
            if (toolRule.getLevel() != 0) {
                Condition condition2 = new Condition();
                condition2.setProperty("level");
                condition2.setOperator(toolRule.getLevelOperator());
                condition2.setValue(toolRule.getLevel());
                conditionList.add(condition2);
            }
            if (toolRule.getYear() != 0) {
                Condition condition3 = new Condition();
                condition3.setProperty("year");
                condition3.setOperator(toolRule.getYearOperator());
                condition3.setValue(toolRule.getYear());
                conditionList.add(condition3);
            }
            rule.setConditions(conditionList);
            rule.setAction(String.valueOf(toolRule.getScore()));
            rules.add(rule);
        }
        return rules;
    }

    @Override
    public List<Rule> createRulesForTypeOfContract(List<TypeOfContractRule> typeOfContractRuleList) {
        List<Rule> rules = new ArrayList<>();
        for (TypeOfContractRule typeOfContractRule : typeOfContractRuleList) {
            if (typeOfContractRule.getTypeOfContract() != null) {
                Rule rule = new Rule("Type Of Contract Rule");
                rule.setDataObject(TypeOfContractRule.class.getName());
                List<Condition> conditionList = new ArrayList<>();
                Condition condition = new Condition();
                condition.setProperty("typeOfContract");
                condition.setOperator(typeOfContractRule.getTypeOfContractOperator());
                condition.setValue(typeOfContractRule.getTypeOfContract());
                conditionList.add(condition);
                rule.setConditions(conditionList);
                rule.setAction(String.valueOf(typeOfContractRule.getScore()));
                rules.add(rule);
            }
        }
        return rules;
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

    @Override
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
                return;
            }

        }
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
}
