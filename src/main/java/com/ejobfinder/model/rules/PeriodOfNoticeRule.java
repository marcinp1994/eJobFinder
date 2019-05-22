package com.ejobfinder.model.rules;

import com.ejobfinder.drools.Condition.Operator;

public class PeriodOfNoticeRule {
    private String periodOfNotice;
    private Operator periodOfNoticeOperator;
    private int score;

    public PeriodOfNoticeRule() {

    }

    public PeriodOfNoticeRule(String periodOfNotice, Operator periodOfNoticeOperator, int score) {
        this.periodOfNotice = periodOfNotice;
        this.periodOfNoticeOperator = periodOfNoticeOperator;
        this.score = score;
    }

    public String getPeriodOfNotice() {
        return periodOfNotice;
    }

    public void setPeriodOfNotice(String periodOfNotice) {
        this.periodOfNotice = periodOfNotice;
    }

    public Operator getPeriodOfNoticeOperator() {
        return periodOfNoticeOperator;
    }

    public void setPeriodOfNoticeOperator(Operator periodOfNoticeOperator) {
        this.periodOfNoticeOperator = periodOfNoticeOperator;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
