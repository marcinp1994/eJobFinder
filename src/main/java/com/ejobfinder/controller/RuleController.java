package com.ejobfinder.controller;

import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.TechnologyFact;
import com.ejobfinder.model.TechnologyRules;
import com.ejobfinder.utils.Condition;
import com.ejobfinder.utils.Condition.Operator;
import com.ejobfinder.utils.DroolsUtility;
import com.ejobfinder.utils.Rule;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RuleController {

    @RequestMapping("/employer/jobOfferInventory/perfectEmployeeRules/{jobId}")
    public String rule(Model model, @PathVariable("jobId") String jobId) throws Exception {
        TechnologyRules technologyRules1 = new TechnologyRules("Java", Operator.GREATER_THAN_OR_EQUAL_TO, 2.0, 3, Operator.GREATER_THAN_OR_EQUAL_TO, 2);
        TechnologyRules technologyRules2 = new TechnologyRules("Spring", Operator.GREATER_THAN_OR_EQUAL_TO, 1.0, 2, Operator.GREATER_THAN_OR_EQUAL_TO, 3);
        TechnologyRules technologyRules3 = new TechnologyRules("SQL", Operator.GREATER_THAN_OR_EQUAL_TO, 3.0, 3, Operator.GREATER_THAN_OR_EQUAL_TO, 1);
        List<TechnologyRules> technologyRulesList = Arrays.asList(technologyRules1, technologyRules2, technologyRules3);

        List<Rule> rules = createRuleForTechnology(technologyRulesList);

        DroolsUtility utility = new DroolsUtility();
        utility.createRules(rules, "Technology.drl", jobId);

        return "candidate";
    }

    @RequestMapping("/fireRule/{jobId}")
    public String fireRule(Model model, @PathVariable("jobId") String jobId) {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        File file = new File("C:\\Users\\m.pudelko\\Desktop\\MAGISTERKA\\eJobFinder\\src\\main\\resources\\rules\\" + jobId + ".drl");
        Resource resource = kieServices.getResources().newFileSystemResource(file).setResourceType(ResourceType.DRL);
        kfs.write(resource);
        KieBuilder kb = kieServices.newKieBuilder(kfs);
        kb.buildAll();

        KieContainer container = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        StatelessKieSession session = container.getKieBase().newStatelessKieSession();

        Candidate candidate = new Candidate();
        TechnologyFact technologyFact = new TechnologyFact("Java", 1, 5);
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

    private static Rule createDiscountOverpriced() {
        Rule rule = new Rule("technology adasd");
        rule.setDataObject(TechnologyFact.class.getName());
        Condition condition1 = new Condition();
        condition1.setProperty("level");
        condition1.setOperator(Condition.Operator.GREATER_THAN);
        condition1.setValue(5);
        Condition condition2 = new Condition();
        condition2.setProperty("name");
        condition2.setOperator(Condition.Operator.EQUAL_TO);
        condition2.setValue("Java");
        rule.setConditions(Arrays.asList(condition1, condition2));
        rule.setAction("3");
        return rule;
    }

    private List<Rule> createRuleForTechnology(List<TechnologyRules> technologyRulesList) {
        int counter = 1;
        List<Rule> rules = new ArrayList<>();
        for (TechnologyRules technologyRule : technologyRulesList) {
            Rule rule = new Rule("technology rule " + counter);
            rule.setDataObject(TechnologyFact.class.getName());
            List<Condition> conditionList = new ArrayList<>();
            Condition condition = new Condition();
            condition.setProperty("name");
            condition.setOperator(Operator.EQUAL_TO);
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
            counter++;
        }
        return rules;
    }

    private static Rule createDiscountOverpriced2() {
        Rule rule = new Rule("technology adasd");
        rule.setDataObject(TechnologyRules.class.getName());
        Condition condition1 = new Condition();
        condition1.setProperty("level");
        condition1.setOperator(Condition.Operator.GREATER_THAN);
        condition1.setValue(3);
        Condition condition2 = new Condition();
        condition2.setProperty("name");
        condition2.setOperator(Condition.Operator.EQUAL_TO);
        condition2.setValue("Java");

        rule.setConditions(Arrays.asList(condition1, condition2));
        rule.setAction("2");

        return rule;
    }

}
