package com.ejobfinder.model.facts;

public class EducationFact {
    private String professionalTitle;
    private String fieldOfStudy;
    private String modeOfStudy;
    private boolean studyAbroad;
    private boolean student;

    public EducationFact(String professionalTitle, String fieldOfStudy, String modeOfStudy, boolean studyAbroad, boolean isStudent) {
        this.professionalTitle = professionalTitle;
        this.fieldOfStudy = fieldOfStudy;
        this.modeOfStudy = modeOfStudy;
        this.studyAbroad = studyAbroad;
        this.student = isStudent;
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
}