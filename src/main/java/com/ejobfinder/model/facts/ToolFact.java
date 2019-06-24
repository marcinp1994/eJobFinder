package com.ejobfinder.model.facts;

import com.ejobfinder.model.Candidate;

import javax.persistence.*;

@Entity
public class ToolFact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int factId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;
    private String name;
    private double year;
    private int level;

    public ToolFact() {
    }

    public ToolFact(String name, double year, int level) {
        this.name = name;
        this.year = year;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
