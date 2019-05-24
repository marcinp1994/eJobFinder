package com.ejobfinder.utils;


import com.ejobfinder.drools.Condition.Operator;

public class OperatorConverter {


    public static Operator convertTextOperatorToSymbolicOperator(String textOperator) {


        switch (textOperator) {
            case "at least": {
                return Operator.GREATER_THAN_OR_EQUAL_TO;
            }
            case "equal": {
                return Operator.EQUAL_TO;
            }
            case "greater than": {
                return Operator.GREATER_THAN;
            }
            case "lower than": {
                return Operator.LESS_THAN;
            }
            case "lower or equal to": {
                return Operator.LESS_THAN_OR_EQUAL_TO;
            }
            default: {
                return Operator.EQUAL_TO;
            }

        }


    }
}
