package com.ejobfinder.model.rules;

import com.ejobfinder.drools.Condition.Operator;

public class TypeOfContractRule {
    private String typeOfContract;
    private Operator typeOfContractOperator;
    private int score;

    public TypeOfContractRule() {

    }

    public TypeOfContractRule(String typeOfContract, Operator typeOfContractOperator, int score) {
        this.typeOfContract = typeOfContract;
        this.typeOfContractOperator = typeOfContractOperator;
        this.score = score;
    }

    public String getTypeOfContract() {
        return typeOfContract;
    }

    public void setTypeOfContract(String typeOfContract) {
        this.typeOfContract = typeOfContract;
    }

    public Operator getTypeOfContractOperator() {
        return typeOfContractOperator;
    }

    public void setTypeOfContractOperator(Operator typeOfContractOperator) {
        this.typeOfContractOperator = typeOfContractOperator;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
