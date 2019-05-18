package com.ejobfinder.model;

import java.util.List;


public class Candidate {
    private List<TechnologyFact> technologyFacts;
    private int score;

    public List<TechnologyFact> getTechnologyFacts() {
        return technologyFacts;
    }

    public void setTechnologyFacts(List<TechnologyFact> technologyFacts) {
        this.technologyFacts = technologyFacts;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void score(int score) {
        this.score += score;
    }
}