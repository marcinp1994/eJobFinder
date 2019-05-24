package com.ejobfinder.service.impl;

import com.ejobfinder.drools.Condition;
import com.ejobfinder.drools.Rule;
import com.ejobfinder.model.SkillFact;
import com.ejobfinder.model.TechnologyFact;
import com.ejobfinder.model.rules.PerfectEmployeeRules;
import com.ejobfinder.model.rules.SkillRule;
import com.ejobfinder.model.rules.TechnologyRule;
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
