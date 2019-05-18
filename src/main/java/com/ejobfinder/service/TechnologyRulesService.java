package com.ejobfinder.service;

import com.ejobfinder.drools.Rule;
import com.ejobfinder.model.rules.TechnologyRule;

import java.util.List;

public interface TechnologyRulesService {
    List<Rule> createRulesForTechnology(List<TechnologyRule> technologyRuleList);
}
