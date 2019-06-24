package com.ejobfinder.model;

import com.ejobfinder.model.facts.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Candidate implements Serializable {

    private static final long serialVersionUID = 5140900014886999914L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "candidateId")
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

    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobOfferApplication> jobOfferApplications = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TechnologyFact> technologyFacts = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SkillFact> skillFacts = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ToolFact> toolFacts = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LanguageFact> languageFacts = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PreviousEmployerFact> previousEmployerFacts = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EducationFact> educationFacts = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LocationFact> locationFacts = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PeriodOfNoticeFact> periodOfNoticeFacts = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SalaryFact> salaryFacts = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TypeOfContractFact> typeOfContractFacts = new HashSet<>();
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WorkingHoursFact> workingHoursFacts = new HashSet<>();


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

    public Set<JobOfferApplication> getJobOfferApplications() {
        return jobOfferApplications;
    }

    public void setJobOfferApplications(Set<JobOfferApplication> jobOfferApplications) {
        this.jobOfferApplications = jobOfferApplications;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<TechnologyFact> getTechnologyFacts() {
        return technologyFacts;
    }

    public void setTechnologyFacts(Set<TechnologyFact> technologyFacts) {
        this.technologyFacts = technologyFacts;
    }

    public Set<SkillFact> getSkillFacts() {
        return skillFacts;
    }

    public void setSkillFacts(Set<SkillFact> skillFacts) {
        this.skillFacts = skillFacts;
    }

    public Set<ToolFact> getToolFacts() {
        return toolFacts;
    }

    public void setToolFacts(Set<ToolFact> toolFacts) {
        this.toolFacts = toolFacts;
    }

    public Set<LanguageFact> getLanguageFacts() {
        return languageFacts;
    }

    public void setLanguageFacts(Set<LanguageFact> languageFacts) {
        this.languageFacts = languageFacts;
    }

    public Set<PreviousEmployerFact> getPreviousEmployerFacts() {
        return previousEmployerFacts;
    }

    public void setPreviousEmployerFacts(Set<PreviousEmployerFact> previousEmployerFacts) {
        this.previousEmployerFacts = previousEmployerFacts;
    }

    public Set<EducationFact> getEducationFacts() {
        return educationFacts;
    }

    public void setEducationFacts(Set<EducationFact> educationFacts) {
        this.educationFacts = educationFacts;
    }

    public Set<LocationFact> getLocationFacts() {
        return locationFacts;
    }

    public void setLocationFacts(Set<LocationFact> locationFacts) {
        this.locationFacts = locationFacts;
    }

    public Set<PeriodOfNoticeFact> getPeriodOfNoticeFacts() {
        return periodOfNoticeFacts;
    }

    public void setPeriodOfNoticeFacts(Set<PeriodOfNoticeFact> periodOfNoticeFacts) {
        this.periodOfNoticeFacts = periodOfNoticeFacts;
    }

    public Set<SalaryFact> getSalaryFacts() {
        return salaryFacts;
    }

    public void setSalaryFacts(Set<SalaryFact> salaryFacts) {
        this.salaryFacts = salaryFacts;
    }

    public Set<TypeOfContractFact> getTypeOfContractFacts() {
        return typeOfContractFacts;
    }

    public void setTypeOfContractFacts(Set<TypeOfContractFact> typeOfContractFacts) {
        this.typeOfContractFacts = typeOfContractFacts;
    }

    public Set<WorkingHoursFact> getWorkingHoursFacts() {
        return workingHoursFacts;
    }

    public void setWorkingHoursFacts(Set<WorkingHoursFact> workingHoursFacts) {
        this.workingHoursFacts = workingHoursFacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return candidateId == candidate.candidateId &&
                enabled == candidate.enabled &&
                score == candidate.score &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(lastName, candidate.lastName) &&
                Objects.equals(candidateEmail, candidate.candidateEmail) &&
                Objects.equals(candidatePhone, candidate.candidatePhone) &&
                Objects.equals(username, candidate.username) &&
                Objects.equals(password, candidate.password) &&
                Objects.equals(jobOfferApplications, candidate.jobOfferApplications) &&
                Objects.equals(technologyFacts, candidate.technologyFacts) &&
                Objects.equals(skillFacts, candidate.skillFacts) &&
                Objects.equals(toolFacts, candidate.toolFacts) &&
                Objects.equals(languageFacts, candidate.languageFacts) &&
                Objects.equals(previousEmployerFacts, candidate.previousEmployerFacts) &&
                Objects.equals(educationFacts, candidate.educationFacts) &&
                Objects.equals(locationFacts, candidate.locationFacts) &&
                Objects.equals(periodOfNoticeFacts, candidate.periodOfNoticeFacts) &&
                Objects.equals(salaryFacts, candidate.salaryFacts) &&
                Objects.equals(typeOfContractFacts, candidate.typeOfContractFacts) &&
                Objects.equals(workingHoursFacts, candidate.workingHoursFacts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(candidateId, name, lastName, candidateEmail, candidatePhone, username, password, jobOfferApplications, technologyFacts, skillFacts, toolFacts, languageFacts, previousEmployerFacts, educationFacts, locationFacts, periodOfNoticeFacts, salaryFacts, typeOfContractFacts, workingHoursFacts, enabled, score);
    }
}
