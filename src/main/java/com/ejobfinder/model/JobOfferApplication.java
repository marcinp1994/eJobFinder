package com.ejobfinder.model;


import javax.persistence.*;

@Entity
public class JobOfferApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String applicationId;
    @ManyToOne(fetch = FetchType.EAGER)
    private JobOffer jobOffer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;
    @Column(name = "score")
    private Integer calculatedScore;
    @Column(name = "percentOfMaxScore")
    private Double percentOfMaxScore;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Integer getCalculatedScore() {
        return calculatedScore;
    }

    public void setCalculatedScore(Integer calculatedScore) {
        this.calculatedScore = calculatedScore;
    }

    public Double getPercentOfMaxScore() {
        return percentOfMaxScore;
    }

    public void setPercentOfMaxScore(Double percentOfMaxScore) {
        this.percentOfMaxScore = percentOfMaxScore;
    }
}
