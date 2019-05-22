package com.ejobfinder.model.rules;

import com.ejobfinder.drools.Condition.Operator;

public class SalaryRule {
    private double amountDown;
    private Operator amountDownOperator;
    private double amountUp;
    private Operator amountUpOperator;
    private int score;

    public SalaryRule() {

    }

    public SalaryRule(double amountDown, Operator amountDownOperator, double amountUp, Operator amountUpOperator, int score) {
        this.amountDown = amountDown;
        this.amountDownOperator = amountDownOperator;
        this.amountUp = amountUp;
        this.amountUpOperator = amountUpOperator;
        this.score = score;
    }

    public double getAmountDown() {
        return amountDown;
    }

    public void setAmountDown(double amountDown) {
        this.amountDown = amountDown;
    }

    public Operator getAmountDownOperator() {
        return amountDownOperator;
    }

    public void setAmountDownOperator(Operator amountDownOperator) {
        this.amountDownOperator = amountDownOperator;
    }

    public double getAmountUp() {
        return amountUp;
    }

    public void setAmountUp(double amountUp) {
        this.amountUp = amountUp;
    }

    public Operator getAmountUpOperator() {
        return amountUpOperator;
    }

    public void setAmountUpOperator(Operator amountUpOperator) {
        this.amountUpOperator = amountUpOperator;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
