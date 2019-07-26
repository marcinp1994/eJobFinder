package com.ejobfinder.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Employer implements Serializable {

    private static final long serialVersionUID = 5140900014886997914L;

    @Id
    @GeneratedValue
    private int employerId;
    private String companyName;
    @NotEmpty(message = "The customer name must not be null.")
    private String name;
    @NotEmpty(message = "The customer email must not be null.")
    private String lastName;
    @NotEmpty(message = "The customer email must not be null.")
    private String employerEmail;
    private String employerPhone;
    @Column(nullable = false)
    private boolean premiumMember;

    @NotEmpty(message = "The customer username must not be null.")
    private String username;

    @NotEmpty(message = "The customer password must not be null.")
    private String password;

    @OneToMany(mappedBy = "employer", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<JobOffer> jobOffers;
    private boolean enabled;

    public Employer() {
        premiumMember = false;
    }
    public String getEmployerEmail() {
        return employerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getEmployerPhone() {
        return employerPhone;
    }

    public void setEmployerPhone(String employerPhone) {
        this.employerPhone = employerPhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public boolean getPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }
}
