package com.ejobfinder.service.impl;

import com.ejobfinder.drools.Condition;
import com.ejobfinder.drools.Rule;
import com.ejobfinder.model.TechnologyFact;
import com.ejobfinder.model.rules.TechnologyRule;
import com.ejobfinder.service.TechnologyRulesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyRulesServiceImpl implements TechnologyRulesService {

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
                condition2.setOperator(Condition.Operator.GREATER_THAN_OR_EQUAL_TO);
                condition2.setValue(technologyRule.getLevel());
                conditionList.add(condition2);
            }
            if (technologyRule.getYear() != 0) {
                Condition condition3 = new Condition();
                condition3.setProperty("year");
                condition3.setOperator(Condition.Operator.GREATER_THAN_OR_EQUAL_TO);
                condition3.setValue(technologyRule.getYear());
                conditionList.add(condition3);
            }
            rule.setConditions(conditionList);
            rule.setAction(String.valueOf(technologyRule.getScore()));
            rules.add(rule);
        }
        return rules;
    }
}
