package com.ejobfinder.model.rules;

import java.util.List;

public class PerfectEmployeeRules {
    private int perfectEmployeeId;
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
}
