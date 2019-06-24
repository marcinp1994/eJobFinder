package com.ejobfinder.model.facts;

import com.ejobfinder.model.Candidate;

import javax.persistence.*;

@Entity
public class PeriodOfNoticeFact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int factId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;
    private String periodOfNotice;

    public PeriodOfNoticeFact() {
    }

    public PeriodOfNoticeFact(String periodOfNotice) {
        this.periodOfNotice = periodOfNotice;
    }

    public String getPeriodOfNotice() {
        return periodOfNotice;
    }

    public void setPeriodOfNotice(String periodOfNotice) {
        this.periodOfNotice = periodOfNotice;
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
