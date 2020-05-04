package com.ejobfinder.drools.utils;

import com.ejobfinder.drools.Rule;
import org.drools.core.spi.KnowledgeHelper;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class DroolsUtility {

    public void createRules(List<Rule> rules, String templatePath, String jobId) throws Exception {
        List<Map<String, Object>> rulesAsParameters = new ArrayList<>(rules.size());
        for (Rule rule : rules) {
            rulesAsParameters.add(rule.asMap());
        }
        ObjectDataCompiler compiler = new ObjectDataCompiler();
        String drl = compiler.compile(rulesAsParameters, Thread.currentThread().getContextClassLoader().getResourceAsStream(templatePath));
        KieServices services = KieServices.Factory.get();
        KieFileSystem system = services.newKieFileSystem();
        system.write("src/main/resources/simple.drl", services.getResources().newReaderResource(new StringReader(drl)));
        services.newKieBuilder(system).buildAll();

        String fileName = jobId + ".drl";
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource("/rules");
        File rulesFile = new File(Objects.requireNonNull(resource).getFile() + fileName);
        FileOutputStream is = new FileOutputStream(rulesFile);
        OutputStreamWriter osw = new OutputStreamWriter(is);
        Writer w = new BufferedWriter(osw);
        w.write(drl);
        w.close();
    }

    public StatelessKieSession loadSession(String jobId) {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = "/rules/" + jobId + ".drl";
        URL resourceURL = classLoader.getResource(fileName);
        File file = new File(Objects.requireNonNull(resourceURL).getFile());
        Resource resource = kieServices.getResources().newFileSystemResource(file).setResourceType(ResourceType.DRL);
        kfs.write(resource);
        KieBuilder kb = kieServices.newKieBuilder(kfs);
        kb.buildAll();

        KieContainer container = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        return container.getKieBase().newStatelessKieSession();
    }

    public static void debug(final KnowledgeHelper helper) {
        System.out.println("Triggered rule: " + helper.getRule().getName());
        if (helper.getMatch() != null && helper.getMatch().getObjects() != null) {
            for (Object object : helper.getMatch().getObjects()) {
                System.out.println("Data object: " + object);
            }
        }
    }
}