package com.ejobfinder.model.rules;

import com.ejobfinder.drools.Condition.Operator;

public class PreviousEmployerRule {
    private String jobTitle;
    private Operator yearOperator;
    private double year;
    private Boolean stillWorking;
    private Boolean haveProfessionalExperience;
    private int score;

    public PreviousEmployerRule() {

    }

    public PreviousEmployerRule(String jobTitle, Operator yearOperator, double year, Boolean isStillWorking, Boolean haveProfessionalExperience, int score) {
        this.jobTitle = jobTitle;
        this.yearOperator = yearOperator;
        this.year = year;
        this.stillWorking = isStillWorking;
        this.haveProfessionalExperience = haveProfessionalExperience;
        this.score = score;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Operator getYearOperator() {
        return yearOperator;
    }

    public void setYearOperator(Operator yearOperator) {
        this.yearOperator = yearOperator;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public Boolean isStillWorking() {
        return stillWorking;
    }

    public void setStillWorking(Boolean stillWorking) {
        this.stillWorking = stillWorking;
    }

    public Boolean isHaveProfessionalExperience() {
        return haveProfessionalExperience;
    }

    public void setHaveProfessionalExperience(Boolean haveProfessionalExperience) {
        this.haveProfessionalExperience = haveProfessionalExperience;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
