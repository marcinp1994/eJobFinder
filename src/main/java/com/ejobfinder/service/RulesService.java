package com.ejobfinder.service;

import com.ejobfinder.drools.Rule;
import com.ejobfinder.model.rules.*;

import java.util.List;

public interface RulesService {
    List<Rule> createRulesForTechnology(List<TechnologyRule> technologyRuleList);

    List<Rule> createRulesForSkills(List<SkillRule> skillRuleList);

    List<Rule> createRulesForWorkingHours(List<WorkingHoursRule> workingHoursRuleList);

    List<Rule> createRulesForEducation(List<EducationRule> educationRuleList);

    List<Rule> createRulesForLanguage(List<LanguageRule> languageRuleList);

    List<Rule> createRulesForLocation(List<LocationRule> locationRuleList);

    List<Rule> createRulesForPeriodOfNotice(List<PeriodOfNoticeRule> periodOfNoticeRuleList);

    List<Rule> createRulesForPreviousEmployee(List<PreviousEmployerRule> previousEmployerRuleList);

    List<Rule> createRulesForSalary(List<SalaryRule> salaryRuleList);

    List<Rule> createRulesForTool(List<ToolRule> toolRuleList);

    List<Rule> createRulesForTypeOfContract(List<TypeOfContractRule> typeOfContractRuleList);

    void deleteRule(String name);

    List<TechnologyRule> addTechnologyRule(TechnologyRule rule);

    List<SkillRule> addSkillRule(SkillRule rule);

    List<ToolRule> addToolRule(ToolRule rule);

    List<LanguageRule> addLanguageRule(LanguageRule rule);

    List<LocationRule> addLocationRule(LocationRule rule);

    List<EducationRule> addEducationRule(EducationRule rule);

    List<SalaryRule> addSalaryRule(SalaryRule rule);

    List<PeriodOfNoticeRule> addPeriodOfNoticeRule(PeriodOfNoticeRule rule);

    List<PreviousEmployerRule> addPreviousEmployerRule(PreviousEmployerRule rule);

    List<TypeOfContractRule> addTypeOfContractRule(TypeOfContractRule rule);

    List<WorkingHoursRule> addWorkingHoursRule(WorkingHoursRule rule);
}
