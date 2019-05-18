package com.ejobfinder.utils;

import org.drools.core.spi.KnowledgeHelper;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DroolsUtility {

    public void createRules(List<Rule> rules, String templatePath, String jobId) throws Exception {
        List<Map<String, Object>> rulesAsParameters = new ArrayList<Map<String, Object>>(rules.size());

        for (Rule rule : rules) {
            rulesAsParameters.add(rule.asMap());
        }

        ObjectDataCompiler compiler = new ObjectDataCompiler();
        //Compiles the list of rules using the template to create a readable Drools Rules Language
        String drl = compiler.compile(rulesAsParameters, Thread.currentThread().getContextClassLoader().getResourceAsStream(templatePath));


        KieServices services = KieServices.Factory.get();
        KieFileSystem system = services.newKieFileSystem();
        system.write("src/main/resources/simple.drl", services.getResources().newReaderResource(new StringReader(drl)));
        services.newKieBuilder(system).buildAll();

        File rulesFile = new File("C:\\Users\\m.pudelko\\Desktop\\MAGISTERKA\\eJobFinder\\src\\main\\resources\\rules\\" + jobId + ".drl");
        FileOutputStream is = new FileOutputStream(rulesFile);
        OutputStreamWriter osw = new OutputStreamWriter(is);
        Writer w = new BufferedWriter(osw);
        w.write(drl);
        w.close();
    }

    /**
     * Debug tool to show what is happening over each triggered execution.<br>
     * Name of rule trigger as well the object inspected are printed.
     *
     * @param helper Injected when a consequence is fired.
     */
    public static void debug(final KnowledgeHelper helper) {
        System.out.println("Triggered rule: " + helper.getRule().getName());
        if (helper.getMatch() != null && helper.getMatch().getObjects() != null) {
            for (Object object : helper.getMatch().getObjects()) {
                System.out.println("Data object: " + object);
            }
        }
    }
}