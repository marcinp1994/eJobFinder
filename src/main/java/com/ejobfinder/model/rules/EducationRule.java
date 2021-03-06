package com.ejobfinder.model.rules;

public class EducationRule {
    private String professionalTitle;
    private String fieldOfStudy;
    private String modeOfStudy;
    private Boolean studyAbroad;
    private Boolean isStudent;
    private int score;

    public EducationRule() {

    }

    public EducationRule(String professionalTitle, String fieldOfStudy, String modeOfStudy, Boolean studyAbroad, Boolean isStudent, int score) {
        this.professionalTitle = professionalTitle;
        this.fieldOfStudy = fieldOfStudy;
        this.modeOfStudy = modeOfStudy;
        this.studyAbroad = studyAbroad;
        this.isStudent = isStudent;
        this.score = score;
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

    public Boolean isStudyAbroad() {
        return studyAbroad;
    }

    public void setStudyAbroad(Boolean studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    public Boolean isStudent() {
        return isStudent;
    }

    public void setStudent(Boolean student) {
        isStudent = student;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
