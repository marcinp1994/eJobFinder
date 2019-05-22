package com.ejobfinder.model.rules;

public class EducationRule {
    private String professionalTitle;
    private String fieldOfStudy;
    private String modeOfStudy;
    private boolean studyAbroad;
    private boolean isStudent;
    private int score;

    public EducationRule() {

    }

    public EducationRule(String professionalTitle, String fieldOfStudy, String modeOfStudy, boolean studyAbroad, boolean isStudent, int score) {
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

    public boolean isStudyAbroad() {
        return studyAbroad;
    }

    public void setStudyAbroad(boolean studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
