package com.ejobfinder.model;


import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;

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
    @Column(name = "isPotential", nullable = false)
    private boolean potential;
    @Column(name = "percentOfMaxScore")
    private Double percentOfMaxScore;
    @Column(name = "employerAcceptance")
    private Boolean employerAcceptancee;
    @Column(name = "candidateAcceptance")
    private Boolean candidateAcceptancee;
    @Column(name = "matchedKeyWords")
    private String matchedKeyWords;
    @Column(name = "percentOfMatchedKeyWords")
    private Double percentOfMatchedKeyWords;

    public JobOfferApplication() {
        employerAcceptancee = null;
        candidateAcceptancee = Boolean.FALSE;
        potential = false;
    }

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

    Integer getCalculatedScore() {
        return calculatedScore;
    }

    public void setCalculatedScore(Integer calculatedScore) {
        this.calculatedScore = calculatedScore;
    }

    Double getPercentOfMaxScore() {
        return percentOfMaxScore;
    }

    public void setPercentOfMaxScore(Double percentOfMaxScore) {
        this.percentOfMaxScore = percentOfMaxScore;
    }

    public Boolean getEmployerAcceptancee() {
        return employerAcceptancee;
    }

    public int getEmployerAcceptanceeAsInt() {
        if (employerAcceptancee == null) {
            return 0;
        }
        if (employerAcceptancee) {
            return 1;
        }
        return -1;
    }


    public void setEmployerAcceptancee(boolean employerAcceptancee) {
        this.employerAcceptancee = employerAcceptancee;
    }

    public boolean getCandidateAcceptancee() {
        return candidateAcceptancee;
    }

    public void setCandidateAcceptancee(boolean candidateAcceptancee) {
        this.candidateAcceptancee = candidateAcceptancee;
    }

    public boolean getPotential() {
        return potential;
    }

    public void setPotential(boolean potential) {
        this.potential = potential;
    }

    public String getMatchedKeyWords() {
        return matchedKeyWords;
    }

    public void setMatchedKeyWords(String matchedKeyWords) {
        this.matchedKeyWords = matchedKeyWords;
    }

    public Double getPercentOfMatchedKeyWords() {
        return percentOfMatchedKeyWords;
    }

    public void setPercentOfMatchedKeyWords(Double percentOfMatchedKeyWords) {
        this.percentOfMatchedKeyWords = percentOfMatchedKeyWords;
    }

    public void setMatchedKeyWords(Set<String> matchedKeyWords) {
        StringJoiner joiner = new StringJoiner(",");
        matchedKeyWords.forEach(joiner::add);
        this.matchedKeyWords = joiner.toString();
    }
}
