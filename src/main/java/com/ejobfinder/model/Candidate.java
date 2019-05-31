package com.ejobfinder.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Candidate implements Serializable {

    private static final long serialVersionUID = 5140900014886999914L;

    @Id
    @GeneratedValue
    private int candidateId;
    @NotEmpty(message = "The candidate name must not be null.")
    private String name;
    @NotEmpty(message = "The candidate email must not be null.")
    private String lastName;
    @NotEmpty(message = "The candidate email must not be null.")
    private String candidateEmail;
    private String candidatePhone;

    @NotEmpty(message = "The candidate username must not be null.")
    private String username;

    @NotEmpty(message = "The candidate password must not be null.")
    private String password;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<JobOffer> jobOffers;

    private boolean enabled;
    //private CandidateFacts candidateFacts;
    private int score;

    public Candidate() {

    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidatePhone() {
        return candidatePhone;
    }

    public void setCandidatePhone(String candidatePhone) {
        this.candidatePhone = candidatePhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
