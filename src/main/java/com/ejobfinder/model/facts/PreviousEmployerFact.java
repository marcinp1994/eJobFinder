package com.ejobfinder.model.facts;

import com.ejobfinder.model.Candidate;

import javax.persistence.*;

@Entity
public class PreviousEmployerFact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int factId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;
    private String jobTitle;
    private double year;
    private Boolean stillWorking;
    private Boolean haveProfessionalExperience;

    public PreviousEmployerFact() {
    }

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

    public int getFactId() {
        return factId;
    }

    public void setFactId(int factId) {
        this.factId = factId;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Boolean getStillWorking() {
        return stillWorking;
    }

    public Boolean getHaveProfessionalExperience() {
        return haveProfessionalExperience;
    }

}
