package com.ejobfinder.model.facts;

public class SalaryFact {
    private double amountUp;
    private double amountDown;

    public SalaryFact(double amountUp, double amountDown) {
        this.amountUp = amountUp;
        this.amountDown = amountDown;
    }

    public double getAmountUp() {
        return amountUp;
    }

    public void setAmountUp(double amountUp) {
        this.amountUp = amountUp;
    }

    public double getAmountDown() {
        return amountDown;
    }

    public void setAmountDown(double amountDown) {
        this.amountDown = amountDown;
    }
}
