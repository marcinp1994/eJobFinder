package com.ejobfinder.model;

import javax.persistence.*;

@Entity
public class Technologies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int technologyId;
    @ManyToOne
    @JoinColumn(name = "perfectEmployeeId")
    private PerfectEmployee perfectEmployee;
    private int level;
    private double experience;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public int getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(int technologyId) {
        this.technologyId = technologyId;
    }

    public PerfectEmployee getPerfectEmployee() {
        return perfectEmployee;
    }

    public void setPerfectEmployee(PerfectEmployee perfectEmployee) {
        this.perfectEmployee = perfectEmployee;
    }

}
