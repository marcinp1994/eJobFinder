package com.ejobfinder.model;

public class TechnologyRules {
    private final String name;
    private String yearOperator;
    private double year;
    private int level;
    private String levelOperator;
    private int score;


    public String getYearOperator() {
        return yearOperator;
    }

    public void setYearOperator(String yearOperator) {
        this.yearOperator = yearOperator;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public String getLevelOperator() {
        return levelOperator;
    }

    public void setLevelOperator(String levelOperator) {
        this.levelOperator = levelOperator;
    }

    public String getName() {
        return name;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public TechnologyRules(String name, int level) {
        this.name = name;
        this.level = level;
        this.score = 0;
    }

    public void score(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
