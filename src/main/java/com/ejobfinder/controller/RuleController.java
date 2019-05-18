package com.ejobfinder.controller;

import com.ejobfinder.drools.Condition.Operator;
import com.ejobfinder.drools.Rule;
import com.ejobfinder.drools.utils.DroolsUtility;
import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.TechnologyFact;
import com.ejobfinder.model.rules.TechnologyRule;
import com.ejobfinder.service.TechnologyRulesService;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class RuleController {

    @Autowired
    private TechnologyRulesService technologyRulesService;

    @Autowired
    private DroolsUtility droolsUtility;

    @RequestMapping("/employer/jobOfferInventory/perfectEmployeeRules/{jobId}")
    public String rule(Model model, @PathVariable("jobId") String jobId) throws Exception {
        TechnologyRule technologyRule1 = new TechnologyRule("Java", Operator.GREATER_THAN_OR_EQUAL_TO, 2.0, 3, Operator.GREATER_THAN_OR_EQUAL_TO, 2);
        TechnologyRule technologyRule2 = new TechnologyRule("Spring", Operator.GREATER_THAN_OR_EQUAL_TO, 1.0, 2, Operator.GREATER_THAN_OR_EQUAL_TO, 3);
        TechnologyRule technologyRule3 = new TechnologyRule("SQL", Operator.GREATER_THAN_OR_EQUAL_TO, 3.0, 3, Operator.GREATER_THAN_OR_EQUAL_TO, 1);
        List<TechnologyRule> technologyRuleList = Arrays.asList(technologyRule1, technologyRule2, technologyRule3);

        List<Rule> rules = technologyRulesService.createRulesForTechnology(technologyRuleList);

        droolsUtility.createRules(rules, "rules/template/PerfectEmployeeRules.drl", jobId);

        return "candidate";
    }

    @RequestMapping("/fireRule/{jobId}")
    public String fireRule(Model model, @PathVariable("jobId") String jobId) {
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

}
