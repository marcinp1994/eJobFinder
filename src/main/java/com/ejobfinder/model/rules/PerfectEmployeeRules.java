package com.ejobfinder.model.rules;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PerfectEmployeeRules implements Serializable {
    private int perfectEmployeeId;
    private String jobId;
    private List<TechnologyRule> technologyRules;
    private List<SkillRule> skillRules;
    private List<ToolRule> toolRules;
    private List<LanguageRule> languageRules;
    private List<PreviousEmployerRule> previousEmployerRules;
    private List<EducationRule> educationRules;
    private List<LocationRule> locationRules;
    private List<PeriodOfNoticeRule> periodOfNoticeRules;
    private List<SalaryRule> salaryRules;
    private List<TypeOfContractRule> typeOfContractRules;
    private List<WorkingHoursRule> workingHoursRules;

    public PerfectEmployeeRules() {
        technologyRules = new ArrayList<TechnologyRule>();
        skillRules = new ArrayList<SkillRule>();
        toolRules = new ArrayList<ToolRule>();
        languageRules = new ArrayList<LanguageRule>();
        previousEmployerRules = new ArrayList<PreviousEmployerRule>();
        locationRules = new ArrayList<LocationRule>();
        periodOfNoticeRules = new ArrayList<PeriodOfNoticeRule>();
        educationRules = new ArrayList<EducationRule>();
        salaryRules = new ArrayList<SalaryRule>();
        typeOfContractRules = new ArrayList<TypeOfContractRule>();
        workingHoursRules = new ArrayList<WorkingHoursRule>();
    }

    public int getPerfectEmployeeId() {
        return perfectEmployeeId;
    }

    public void setPerfectEmployeeId(int perfectEmployeeId) {
        this.perfectEmployeeId = perfectEmployeeId;
    }

    public List<TechnologyRule> getTechnologyRules() {
        return technologyRules;
    }

    public void setTechnologyRules(List<TechnologyRule> technologyRules) {
        this.technologyRules = technologyRules;
    }

    public List<SkillRule> getSkillRules() {
        return skillRules;
    }

    public void setSkillRules(List<SkillRule> skillRules) {
        this.skillRules = skillRules;
    }

    public List<ToolRule> getToolRules() {
        return toolRules;
    }

    public void setToolRules(List<ToolRule> toolRules) {
        this.toolRules = toolRules;
    }

    public List<LanguageRule> getLanguageRules() {
        return languageRules;
    }

    public void setLanguageRules(List<LanguageRule> languageRules) {
        this.languageRules = languageRules;
    }

    public List<PreviousEmployerRule> getPreviousEmployerRules() {
        return previousEmployerRules;
    }

    public void setPreviousEmployerRules(List<PreviousEmployerRule> previousEmployerRules) {
        this.previousEmployerRules = previousEmployerRules;
    }

    public List<EducationRule> getEducationRules() {
        return educationRules;
    }

    public void setEducationRules(List<EducationRule> educationRules) {
        this.educationRules = educationRules;
    }

    public List<LocationRule> getLocationRules() {
        return locationRules;
    }

    public void setLocationRules(List<LocationRule> locationRules) {
        this.locationRules = locationRules;
    }

    public List<PeriodOfNoticeRule> getPeriodOfNoticeRules() {
        return periodOfNoticeRules;
    }

    public void setPeriodOfNoticeRules(List<PeriodOfNoticeRule> periodOfNoticeRules) {
        this.periodOfNoticeRules = periodOfNoticeRules;
    }

    public List<SalaryRule> getSalaryRules() {
        return salaryRules;
    }

    public void setSalaryRules(List<SalaryRule> salaryRules) {
        this.salaryRules = salaryRules;
    }

    public List<TypeOfContractRule> getTypeOfContractRules() {
        return typeOfContractRules;
    }

    public void setTypeOfContractRules(List<TypeOfContractRule> typeOfContractRules) {
        this.typeOfContractRules = typeOfContractRules;
    }

    public List<WorkingHoursRule> getWorkingHoursRules() {
        return workingHoursRules;
    }

    public void setWorkingHoursRules(List<WorkingHoursRule> workingHoursRules) {
        this.workingHoursRules = workingHoursRules;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
