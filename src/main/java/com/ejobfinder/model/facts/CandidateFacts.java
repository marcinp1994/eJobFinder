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
    private List<TechnologyFact> technologyFacts;
    private List<SkillFact> skillFacts;
    private List<ToolFact> toolFacts;
    private List<LanguageFact> languageFacts;
    private List<PreviousEmployerFact> previousEmployerFacts;
    private List<EducationFact> educationFacts;
    private List<LocationFact> locationFacts;
    private List<PeriodOfNoticeFact> periodOfNoticeFacts;
    private List<SalaryFact> salaryFacts;
    private List<TypeOfContractFact> typeOfContractFacts;
    private List<WorkingHoursFact> workingHoursFacts;

    public CandidateFacts() {
        technologyFacts = new ArrayList<TechnologyFact>();
        skillFacts = new ArrayList<SkillFact>();
        toolFacts = new ArrayList<ToolFact>();
        languageFacts = new ArrayList<LanguageFact>();
        previousEmployerFacts = new ArrayList<PreviousEmployerFact>();
        locationFacts = new ArrayList<LocationFact>();
        periodOfNoticeFacts = new ArrayList<PeriodOfNoticeFact>();
        educationFacts = new ArrayList<EducationFact>();
        salaryFacts = new ArrayList<SalaryFact>();
        typeOfContractFacts = new ArrayList<TypeOfContractFact>();
        workingHoursFacts = new ArrayList<WorkingHoursFact>();
    }

    public List<TechnologyFact> addTechnologyFact(TechnologyFact fact) {
        this.technologyFacts.add(fact);
        return this.technologyFacts;
    }

    public List<SkillFact> addSkillFact(SkillFact fact) {
        this.skillFacts.add(fact);
        return this.getSkillFacts();
    }

    public List<ToolFact> addToolFact(ToolFact fact) {
        this.getToolFacts().add(fact);
        return this.getToolFacts();
    }

    public List<LanguageFact> addLanguageFact(LanguageFact fact) {
        this.getLanguageFacts().add(fact);
        return this.getLanguageFacts();
    }

    public List<LocationFact> addLocationFact(LocationFact fact) {
        this.getLocationFacts().add(fact);
        return this.getLocationFacts();
    }

    public List<EducationFact> addEducationFact(EducationFact fact) {
        this.getEducationFacts().add(fact);
        return this.getEducationFacts();
    }

    public List<SalaryFact> addSalaryFact(SalaryFact fact) {
        this.getSalaryFacts().add(fact);
        return this.getSalaryFacts();
    }

    public List<PeriodOfNoticeFact> addPeriodOfNoticeFact(PeriodOfNoticeFact fact) {
        this.getPeriodOfNoticeFacts().add(fact);
        return this.getPeriodOfNoticeFacts();
    }

    public List<PreviousEmployerFact> addPreviousEmployerFact(PreviousEmployerFact fact) {
        this.getPreviousEmployerFacts().add(fact);
        return this.getPreviousEmployerFacts();
    }

    public List<TypeOfContractFact> addTypeOfContractFact(TypeOfContractFact fact) {
        this.getTypeOfContractFacts().add(fact);
        return this.getTypeOfContractFacts();
    }

    public List<WorkingHoursFact> addWorkingHoursFact(WorkingHoursFact fact) {
        this.getWorkingHoursFacts().add(fact);
        return this.getWorkingHoursFacts();
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