package com.ejobfinder.model.facts;

public class ToolFact {
    private String name;
    private double year;
    private int level;

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
}
