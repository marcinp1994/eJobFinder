package com.ejobfinder.model.facts;

import com.ejobfinder.model.Candidate;

import javax.persistence.*;

@Entity
public class SalaryFact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int factId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;
    private double amountUp;
    private double amountDown;

    public SalaryFact() {
    }

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

}
