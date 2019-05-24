package com.ejobfinder.service;

import com.ejobfinder.drools.Rule;
import com.ejobfinder.model.rules.SkillRule;
import com.ejobfinder.model.rules.TechnologyRule;

import java.util.List;

public interface RulesService {
    List<Rule> createRulesForTechnology(List<TechnologyRule> technologyRuleList);

    List<Rule> createRulesForSkills(List<SkillRule> skillRuleList);

    void deleteRule(String name);
}
