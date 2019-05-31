package com.ejobfinder.model;

import com.ejobfinder.model.facts.CandidateFacts;


public class Candidate {
    CandidateFacts candidateFacts;
    private int score;

    public Candidate() {

    }

    public Candidate(CandidateFacts candidateFacts, int score) {
        this.candidateFacts = candidateFacts;
        this.score = score;
    }

    public CandidateFacts getCandidateFacts() {
        return candidateFacts;
    }

    public void setCandidateFacts(CandidateFacts candidateFacts) {
        this.candidateFacts = candidateFacts;
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
