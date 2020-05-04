package com.ejobfinder.model.facts;


import org.h2.util.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CandidateFacts implements Serializable {
    private int candidateId;
    private final List<TechnologyFact> technologyFacts;
    private final List<SkillFact> skillFacts;
    private final List<ToolFact> toolFacts;
    private final List<LanguageFact> languageFacts;
    private final List<PreviousEmployerFact> previousEmployerFacts;
    private final List<EducationFact> educationFacts;
    private final List<LocationFact> locationFacts;
    private final List<PeriodOfNoticeFact> periodOfNoticeFacts;
    private final List<SalaryFact> salaryFacts;
    private final List<TypeOfContractFact> typeOfContractFacts;
    private final List<WorkingHoursFact> workingHoursFacts;

    public CandidateFacts() {
        technologyFacts = new ArrayList<>();
        skillFacts = new ArrayList<>();
        toolFacts = new ArrayList<>();
        languageFacts = new ArrayList<>();
        previousEmployerFacts = new ArrayList<>();
        locationFacts = new ArrayList<>();
        periodOfNoticeFacts = new ArrayList<>();
        educationFacts = new ArrayList<>();
        salaryFacts = new ArrayList<>();
        typeOfContractFacts = new ArrayList<>();
        workingHoursFacts = new ArrayList<>();
    }

    public void addTechnologyFact(TechnologyFact fact) {
        this.technologyFacts.add(fact);
    }

    public void addSkillFact(SkillFact fact) {
        this.skillFacts.add(fact);
    }

    public void addToolFact(ToolFact fact) {
        this.getToolFacts().add(fact);
    }

    public void addLanguageFact(LanguageFact fact) {
        this.getLanguageFacts().add(fact);
    }

    public void addLocationFact(LocationFact fact) {
        this.getLocationFacts().add(fact);
    }

    public void addEducationFact(EducationFact fact) {
        this.getEducationFacts().add(fact);
    }

    public void addSalaryFact(SalaryFact fact) {
        this.getSalaryFacts().add(fact);
    }

    public void addPeriodOfNoticeFact(PeriodOfNoticeFact fact) {
        this.getPeriodOfNoticeFacts().add(fact);
    }

    public void addPreviousEmployerFact(PreviousEmployerFact fact) {
        this.getPreviousEmployerFacts().add(fact);
    }

    public void addTypeOfContractFact(TypeOfContractFact fact) {
        this.getTypeOfContractFacts().add(fact);
    }

    public void addWorkingHoursFact(WorkingHoursFact fact) {
        this.getWorkingHoursFacts().add(fact);
    }

    public void deleteFact(String name) {
        Integer idx = getIndexFromName(name);
        if (idx != null) {
            int index = idx - 1;
            if (name.contains("_tech_")) {
                this.getTechnologyFacts().remove(index);
                return;
            }
            if (name.contains("_skill_")) {
                this.getSkillFacts().remove(index);
                return;
            }

            if (name.contains("_tool_")) {
                this.getToolFacts().remove(index);
                return;
            }

            if (name.contains("_lang_")) {
                this.getLanguageFacts().remove(index);
                return;
            }

            if (name.contains("_loc_")) {
                this.getLocationFacts().remove(index);
                return;
            }

            if (name.contains("_workH_")) {
                this.getWorkingHoursFacts().remove(index);
                return;
            }

            if (name.contains("_contr_")) {
                this.getTypeOfContractFacts().remove(index);
                return;
            }

            if (name.contains("_period_")) {
                this.getPeriodOfNoticeFacts().remove(index);
                return;
            }

            if (name.contains("_edu_")) {
                this.getEducationFacts().remove(index);
                return;
            }

            if (name.contains("_prev_")) {
                this.getPreviousEmployerFacts().remove(index);
                return;
            }

            if (name.contains("_salary_")) {
                this.getSalaryFacts().remove(index);
            }

        }
    }

    private Integer getIndexFromName(String name) {
        if (name != null) {
            String[] split = name.split("_");
            String last = split[split.length - 1];
            if (StringUtils.isNumber(last)) {
                return Integer.valueOf(last);
            }
        }
        return null;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public List<TechnologyFact> getTechnologyFacts() {
        return technologyFacts;
    }

    public List<SkillFact> getSkillFacts() {
        return skillFacts;
    }

    public List<ToolFact> getToolFacts() {
        return toolFacts;
    }

    public List<LanguageFact> getLanguageFacts() {
        return languageFacts;
    }

    public List<PreviousEmployerFact> getPreviousEmployerFacts() {
        return previousEmployerFacts;
    }

    public List<EducationFact> getEducationFacts() {
        return educationFacts;
    }

    public List<LocationFact> getLocationFacts() {
        return locationFacts;
    }

    public List<PeriodOfNoticeFact> getPeriodOfNoticeFacts() {
        return periodOfNoticeFacts;
    }

    public List<SalaryFact> getSalaryFacts() {
        return salaryFacts;
    }

    public List<TypeOfContractFact> getTypeOfContractFacts() {
        return typeOfContractFacts;
    }

    public List<WorkingHoursFact> getWorkingHoursFacts() {
        return workingHoursFacts;
    }
}