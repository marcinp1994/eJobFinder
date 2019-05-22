package com.ejobfinder.model.rules;

import com.ejobfinder.drools.Condition.Operator;

public class PreviousEmployerRule {
    private String jobTitle;
    private Operator yearOperator;
    private double year;
    private boolean isStillWorking;
    private boolean haveProfessionalExperience;
    private int score;

    public PreviousEmployerRule() {

    }

    public PreviousEmployerRule(String jobTitle, Operator yearOperator, double year, boolean isStillWorking, boolean haveProfessionalExperience, int score) {
        this.jobTitle = jobTitle;
        this.yearOperator = yearOperator;
        this.year = year;
        this.isStillWorking = isStillWorking;
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

    public boolean isStillWorking() {
        return isStillWorking;
    }

    public void setStillWorking(boolean stillWorking) {
        isStillWorking = stillWorking;
    }

    public boolean isHaveProfessionalExperience() {
        return haveProfessionalExperience;
    }

    public void setHaveProfessionalExperience(boolean haveProfessionalExperience) {
        this.haveProfessionalExperience = haveProfessionalExperience;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
