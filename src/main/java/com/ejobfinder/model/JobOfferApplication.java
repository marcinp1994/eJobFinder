package com.ejobfinder.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class JobOfferApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String applicationId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private JobOffer jobOffer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    @Column(name = "score")
    private Integer calculatedScore;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOfferApplication jobOfferApplication = (JobOfferApplication) o;
        return Objects.equals(applicationId, jobOfferApplication.applicationId) &&
                Objects.equals(jobOffer, jobOfferApplication.jobOffer) &&
                Objects.equals(candidate, jobOfferApplication.candidate) &&
                Objects.equals(calculatedScore, jobOfferApplication.calculatedScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, jobOffer, candidate, calculatedScore);
    }

}
