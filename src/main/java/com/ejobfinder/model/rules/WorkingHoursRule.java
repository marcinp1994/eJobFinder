package com.ejobfinder.model.rules;

import com.ejobfinder.drools.Condition.Operator;

public class WorkingHoursRule {
    private String workingHours;
    private Operator workingHoursOperator;
    private int score;

    public WorkingHoursRule() {

    }

    public WorkingHoursRule(String workingHours, Operator workingHoursOperator, int score) {
        this.workingHours = workingHours;
        this.workingHoursOperator = workingHoursOperator;
        this.score = score;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public Operator getWorkingHoursOperator() {
        return workingHoursOperator;
    }

    public void setWorkingHoursOperator(Operator workingHoursOperator) {
        this.workingHoursOperator = workingHoursOperator;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
