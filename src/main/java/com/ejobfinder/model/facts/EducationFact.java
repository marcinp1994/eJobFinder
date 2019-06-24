package com.ejobfinder.model.facts;

import com.ejobfinder.model.Candidate;

import javax.persistence.*;

@Entity
public class EducationFact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int factId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;
    private String professionalTitle;
    private String fieldOfStudy;
    private String modeOfStudy;
    private boolean studyAbroad;
    private boolean student;

    public EducationFact() {
    }

    public EducationFact(String professionalTitle, String fieldOfStudy, String modeOfStudy, boolean studyAbroad, boolean isStudent) {
        this.professionalTitle = professionalTitle;
        this.fieldOfStudy = fieldOfStudy;
        this.modeOfStudy = modeOfStudy;
        this.studyAbroad = studyAbroad;
        this.student = isStudent;
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

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getModeOfStudy() {
        return modeOfStudy;
    }

    public void setModeOfStudy(String modeOfStudy) {
        this.modeOfStudy = modeOfStudy;
    }

    public boolean studyAbroad() {
        return studyAbroad;
    }

    public void setStudyAbroad(boolean studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    public boolean student() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public int getCandidateId() {
        return candidate.getCandidateId();
    }

    public void setCandidateId(int candidateId) {
        //this.candidateId = candidateId;
    }

    public boolean isStudyAbroad() {
        return studyAbroad;
    }

    public boolean isStudent() {
        return student;
    }

}
