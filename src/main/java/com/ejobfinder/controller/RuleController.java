package com.ejobfinder.controller;

import com.ejobfinder.model.TechnologyRules;
import com.ejobfinder.utils.Condition;
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

        List<Rule> rules = new ArrayList<Rule>();
        //Load each business rule
        rules.add(createDiscountOverpriced());
        rules.add(createDiscountOverpriced2());

        DroolsUtility utility = new DroolsUtility();
        utility.createRules(rules, "Technology.drl", jobId);

        //Define the products to be processed using our rules

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
        //Create a session to operate Drools in memory

        //Define the products to be processed using our rules
        TechnologyRules technologyRules = new TechnologyRules("sdfg", 4);
		/*
		Now, the magic happens!
		For each product to be processed, we have to face it over rules to get, or not, a discounted price.
		*/
        System.out.println("Applying over " + technologyRules.getName() + " with price $" + technologyRules.getLevel() + "...");
        session.setGlobal("technology", technologyRules);
        session.execute(technologyRules);
        System.out.println("...price after review: $" + technologyRules.getScore());

        //Define the products to be processed using our rules

        return "candidate";
    }

    private static Rule createDiscountOverpriced() {
        //First of all, we create a rule giving it a friendly name
        Rule rule = new Rule("technology adasd");
        //Here we need to say what kind of object will be processed
        rule.setDataObject(TechnologyRules.class.getName());

        //As expected, a rule needs condition to exists. So, let's create it...

        Condition condition1 = new Condition();
        //What data, or property, will be checked
        condition1.setProperty("level");
        //What kind of check to do
        condition1.setOperator(Condition.Operator.GREATER_THAN);
        //What is the value to check
        condition1.setValue(5);

        Condition condition2 = new Condition();
        //What data, or property, will be checked
        condition2.setProperty("name");
        //What kind of check to do
        condition2.setOperator(Condition.Operator.EQUAL_TO);
        //What is the value to check
        condition2.setValue("Java");

        //Next, is needed to set rule's condition]
        rule.setConditions(Arrays.asList(condition1, condition2));
        //Finally, this is what will be done when ours condition is satisfied
        rule.setAction("3");

        return rule;
    }

    private static Rule createDiscountOverpriced2() {
        //First of all, we create a rule giving it a friendly name
        Rule rule = new Rule("technology adasd");
        //Here we need to say what kind of object will be processed
        rule.setDataObject(TechnologyRules.class.getName());

        //As expected, a rule needs condition to exists. So, let's create it...

        Condition condition1 = new Condition();
        //What data, or property, will be checked
        condition1.setProperty("level");
        //What kind of check to do
        condition1.setOperator(Condition.Operator.GREATER_THAN);
        //What is the value to check
        condition1.setValue(3);

        Condition condition2 = new Condition();
        //What data, or property, will be checked
        condition2.setProperty("name");
        //What kind of check to do
        condition2.setOperator(Condition.Operator.EQUAL_TO);
        //What is the value to check
        condition2.setValue("Java");

        //Next, is needed to set rule's condition]
        rule.setConditions(Arrays.asList(condition1, condition2));
        //Finally, this is what will be done when ours condition is satisfied
        rule.setAction("2");

        return rule;
    }


}
