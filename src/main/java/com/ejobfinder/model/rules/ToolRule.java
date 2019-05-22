package com.ejobfinder.model.rules;

import com.ejobfinder.drools.Condition.Operator;

public class ToolRule {
    private String name;
    private Operator yearOperator;
    private double year;
    private int level;
    private Operator levelOperator;
    private int score;

    public ToolRule() {

    }

    public ToolRule(String name, Operator yearOperator, double year, int level, Operator levelOperator, int score) {
        this.name = name;
        this.yearOperator = yearOperator;
        this.year = year;
        this.level = level;
        this.levelOperator = levelOperator;
        this.score = score;
    }

    public String getName() {
        return name;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Operator getLevelOperator() {
        return levelOperator;
    }

    public void setLevelOperator(Operator levelOperator) {
        this.levelOperator = levelOperator;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
