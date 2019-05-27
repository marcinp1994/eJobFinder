package com.ejobfinder.service;

import com.ejobfinder.drools.Rule;
import com.ejobfinder.model.rules.*;

import java.util.List;

public interface RulesService {
    List<Rule> createRulesForTechnology(List<TechnologyRule> technologyRuleList);

    List<Rule> createRulesForSkills(List<SkillRule> skillRuleList);

    List<Rule> createRulesForWorkingHours(List<WorkingHoursRule> workingHoursRuleList);

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
