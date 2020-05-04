
package com.ejobfinder.drools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Rule {

    private final String name;
    private String object;
    private List<Condition> conditions = new ArrayList<>();
    private String action;
    private int score;

    public Rule(String name) {
        this.name = name;
    }

    public enum Attribute {
        RULE_NAME("name"),
        DATA_OBJECT("object"),
        CONDITIONAL("conditional"),
        ACTION("action");

        private final String name;

        Attribute(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Override
    public String toString() {

        String me = "[" + this.getClass().getName() + " | name = " +
                name +
                " | object = " +
                object +
                " | conditions = " +
                ((conditions == null) ? "null" : conditions.size()) +
                " | action = " +
                action +
                "]";
        return me;
    }

    public String conditionAsDRL() throws IllegalStateException, IllegalArgumentException {
        if ((conditions == null) || (conditions.isEmpty())) {
            throw new IllegalStateException("You must declare at least one condition to be evaluated.");
        }

        StringBuilder drl = new StringBuilder();
        for (int i = 0; i < conditions.size(); i++) {
            Condition condition = conditions.get(i);
            drl.append("(");
            drl.append(condition.buildExpression());
            drl.append(")");
            if ((i + 1) < conditions.size()) {
                drl.append(" && ");
            }
        }

        return drl.toString();
    }

    public Map<String, Object> asMap() throws IllegalStateException {
        if ((name == null) || (object == null) || (action == null)) {
            throw new IllegalArgumentException("The rule has no name, object to be evaluated or action to be accomplished.");
        }

        Map<String, Object> attributes = new HashMap<>();
        attributes.put(Rule.Attribute.RULE_NAME.toString(), name);
        attributes.put(Rule.Attribute.DATA_OBJECT.toString(), object);
        attributes.put(Rule.Attribute.CONDITIONAL.toString(), conditionAsDRL());
        attributes.put(Rule.Attribute.ACTION.toString(), action);

        return attributes;
    }

    public Condition addCondition(String property, Condition.Operator operator, Object value) {
        Condition condition = new Condition(property, operator, value);
        conditions.add(condition);

        return condition;
    }

    public String getName() {
        return name;
    }

    public String getDataObject() {
        return object;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public Condition getCondition() {
        if ((conditions == null) || (conditions.isEmpty())) {
            return null;
        } else {
            return conditions.get(0);
        }
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public void setCondition(Condition condition) {
        conditions = new ArrayList<>();
        conditions.add(condition);
    }

    public String getAction() {
        return action;
    }

    public void setDataObject(String dataObject) {
        this.object = dataObject;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}