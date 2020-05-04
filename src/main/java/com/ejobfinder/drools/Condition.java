package com.ejobfinder.drools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Condition {

    private String property;
    private Object value;
    private Operator operator;

    public enum Operator {

        NOT_EQUAL_TO("Not equal to", "!=", (new ArrayList<Class>() {
            {
                add(String.class);
                add(Double.class);
                add(Float.class);
                add(Integer.class);
                add(Short.class);
                add(Long.class);
                add(Date.class);
            }
        })),

        EQUAL_TO("Equal to", "==", (new ArrayList<Class>() {
            {
                add(String.class);
                add(Double.class);
                add(Float.class);
                add(Integer.class);
                add(Short.class);
                add(Long.class);
                add(Date.class);
                add(Boolean.class);
            }
        })),

        CONTAINS("Contains this", "?", (new ArrayList<Class>() {
            {
                add(String.class);
            }
        })),

        GREATER_THAN("Greater than", ">", (new ArrayList<Class>() {
            {
                add(Double.class);
                add(Float.class);
                add(Integer.class);
                add(Short.class);
                add(Long.class);
                add(Date.class);
            }
        })),

        LESS_THAN("Less than", "<", (new ArrayList<Class>() {
            {
                add(Double.class);
                add(Float.class);
                add(Integer.class);
                add(Short.class);
                add(Long.class);
                add(Date.class);
            }
        })),

        GREATER_THAN_OR_EQUAL_TO("Greater or equal to", ">=", (new ArrayList<Class>() {
            {
                add(Double.class);
                add(Float.class);
                add(Integer.class);
                add(Short.class);
                add(Long.class);
                add(Date.class);
            }
        })),

        LESS_THAN_OR_EQUAL_TO("Less or equal to", "<=", (new ArrayList<Class>() {
            {
                add(Double.class);
                add(Float.class);
                add(Integer.class);
                add(Short.class);
                add(Long.class);
                add(Date.class);
            }
        }));


        private final String description;
        private final String operation;
        private final List<Class> acceptables;

        Operator(String description, String operation, List<Class> acceptables) {
            this.description = description;
            this.operation = operation;
            this.acceptables = acceptables;
        }

        @Override
        public String toString() {
            StringBuilder me = new StringBuilder("[" + this.getClass().getName());
            me.append(" | name = ");
            me.append(name());
            me.append(" | description = ");
            me.append(description);
            me.append(" | operation = ");
            me.append(operation);
            me.append(" | acceptables = ");
            me.append(Arrays.toString(acceptables.toArray()));
            me.append("]");

            return me.toString();
        }

        public String getDescription() {
            return description;
        }

        public String getOperation() {
            return operation;
        }

        public boolean isComparable(Class clazz) {
            for (Class accept : acceptables) {
                if (accept.equals(clazz)) {
                    return true;
                }
            }

            return false;
        }

        public static Operator fromDescription(String description) throws EnumConstantNotPresentException {
            for (Operator operator : Operator.values()) {
                if (operator.getDescription().equals(description)) {
                    return operator;
                }
            }

            throw new EnumConstantNotPresentException(Operator.class, "? (" + description + ")");
        }

        public static Operator fromOperation(String operation) throws EnumConstantNotPresentException {
            for (Operator operator : Operator.values()) {
                if (operator.getOperation().equals(operation)) {
                    return operator;
                }
            }

            throw new EnumConstantNotPresentException(Operator.class, "? (" + operation + ")");
        }
    }

    public Condition() {
    }

    public Condition(String property, Operator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public String buildExpression() throws IllegalArgumentException {
        StringBuilder drl = new StringBuilder();

        if (value instanceof String) {
            drl.append(expressionForStringValue());
        } else if (value instanceof Number) {
            drl.append(expressionForNumberValue());
        } else if (value instanceof Date) {
            drl.append(expressionForDateValue());
        } else if (value instanceof Boolean) {
            drl.append(expressionForBooleanValue());
        } else {
            throw new IllegalArgumentException("The class " + value.getClass().getSimpleName() + " of value is not acceptable.");
        }

        return drl.toString();
    }

    private String expressionForStringValue() throws IllegalArgumentException {
        StringBuilder drl = new StringBuilder();

        if (operator.isComparable(String.class)) {
            if (operator.equals(Condition.Operator.CONTAINS)) {
                drl.append(property).append(".toUpperCase().contains(\"").append(((String) value).toUpperCase()).append("\")");
            } else {
                drl.append(property).append(" ").append(operator.getOperation()).append(" ").append("\"").append(value).append("\"");
            }
        } else {
            throw new IllegalArgumentException("Is not possible to use the operator " + operator.getDescription() + " to a " + value.getClass().getSimpleName() + " object.");
        }

        return drl.toString();
    }

    private String expressionForNumberValue() throws IllegalArgumentException {
        StringBuilder drl = new StringBuilder();

        if ((operator.isComparable(Short.class)) || (operator.isComparable(Integer.class)) || (operator.isComparable(Long.class))
                || (operator.isComparable(Double.class)) || (operator.isComparable(Float.class))) {
            drl.append(property).append(" ").append(operator.getOperation()).append(" ").append(value);
        } else {
            throw new IllegalArgumentException("Is not possible to use the operator " + operator.getDescription() + " to a " + value.getClass().getSimpleName() + " object.");
        }

        return drl.toString();
    }

    private String expressionForDateValue() throws IllegalArgumentException {
        StringBuilder drl = new StringBuilder();

        if (operator.isComparable(Date.class)) {
            drl.append(property).append(" ").append(operator.getOperation()).append(" (new SimpleDateFormat(\"dd/MM/yyyy HH:mm:ss\")).parse(\"").append((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format((Date) value)).append("\")");
        } else {
            throw new IllegalArgumentException("Is not possible to use the operator " + operator.getDescription() + " to a " + value.getClass().getSimpleName() + " object.");
        }

        return drl.toString();
    }

    private String expressionForBooleanValue() throws IllegalArgumentException {
        StringBuilder drl = new StringBuilder();

        if ((operator.isComparable(Boolean.class))) {
            drl.append(property).append(" ").append(operator.getOperation()).append(" ").append(value);
        } else {
            throw new IllegalArgumentException("Is not possible to use the operator " + operator.getDescription() + " to a " + value.getClass().getSimpleName() + " object.");
        }

        return drl.toString();
    }

    public String getProperty() {
        return property;
    }

    public Object getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setOperator(Condition.Operator operator) {
        this.operator = operator;
    }
}