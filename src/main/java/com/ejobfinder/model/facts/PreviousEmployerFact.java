package com.ejobfinder.model.facts;

public class PreviousEmployerFact {
    private String jobTitle;
    private double year;
    private Boolean stillWorking;
    private Boolean haveProfessionalExperience;

    public PreviousEmployerFact(String jobTitle, double year, Boolean isStillWorking, Boolean haveProfessionalExperience) {
        this.jobTitle = jobTitle;
        this.year = year;
        this.stillWorking = isStillWorking;
        this.haveProfessionalExperience = haveProfessionalExperience;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public Boolean stillWorking() {
        return stillWorking;
    }

    public void setStillWorking(Boolean stillWorking) {
        this.stillWorking = stillWorking;
    }

    public Boolean HaveProfessionalExperience() {
        return haveProfessionalExperience;
    }

    public void setHaveProfessionalExperience(Boolean haveProfessionalExperience) {
        this.haveProfessionalExperience = haveProfessionalExperience;
    }
}
