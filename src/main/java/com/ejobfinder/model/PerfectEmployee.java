package com.ejobfinder.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class PerfectEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int perfectEmployeeId;
    @OneToOne
    @JoinColumn(name = "jobId")
    private JobOffer jobOffer;

    @OneToMany(mappedBy = "perfectEmployee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Technologies> technologies;

    public int getPerfectEmployeeId() {
        return perfectEmployeeId;
    }

    public void setPerfectEmployeeId(int perfectEmployeeId) {
        this.perfectEmployeeId = perfectEmployeeId;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

    public List<Technologies> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technologies> technologies) {
        this.technologies = technologies;
    }

}
