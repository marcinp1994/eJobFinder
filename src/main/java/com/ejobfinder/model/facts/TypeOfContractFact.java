package com.ejobfinder.model.facts;

import com.ejobfinder.model.Candidate;

import javax.persistence.*;

@Entity
public class TypeOfContractFact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int factId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;
    private String typeOfContract;

    public TypeOfContractFact() {
    }

    public TypeOfContractFact(String typeOfContract) {
        this.typeOfContract = typeOfContract;
    }

    public String getTypeOfContract() {
        return typeOfContract;
    }

    public void setTypeOfContract(String typeOfContract) {
        this.typeOfContract = typeOfContract;
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
