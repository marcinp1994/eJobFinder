package com.ejobfinder.model.rules;

import com.ejobfinder.drools.Condition.Operator;

public class LanguageRule {
    private String name;
    private int level;
    private Operator levelOperator;
    private int score;

    public LanguageRule() {

    }

    public LanguageRule(String name, int level, Operator levelOperator, int score) {
        this.name = name;
        this.level = level;
        this.levelOperator = levelOperator;
        this.score = score;
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
