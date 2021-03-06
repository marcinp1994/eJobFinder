package com.ejobfinder.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String jobId;
    @NotEmpty(message = "Position must not be empty!")
    private String position;
    @NotEmpty(message = "Company Name must not be empty!")
    private String companyName;
    @NotEmpty(message = "Short description must not be empty!")
    private String shortDescription;
    @NotNull
    @Type(type = "text")
    private String description;
    @NotNull
    @NotEmpty(message = "Category must not be empty!")
    private String category;
    private String jobOfferStatus;
    @NotNull
    @Type(type = "text")
    private String requirements;
    @NotNull
    @Type(type = "text")
    private String responsibilities;
    @NotNull
    @Type(type = "text")
    private String preferredSkills;
    private Integer thresholdPercentagePoints;
    private Integer maximalPoints;
    @NotNull
    @Type(type = "text")
    private String benefits;
    private Boolean containsRules = Boolean.FALSE;
    @Type(type = "text")
    private String additionalInfo;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;
    private String tags;
    @Transient
    private MultipartFile companyLogo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private Employer employer;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn()
    private Location location;
    @OneToMany(mappedBy = "jobOffer", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<JobOfferApplication> jobOfferApplications = new HashSet<>();
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJobOfferStatus() {
        return jobOfferStatus;
    }

    public void setJobOfferStatus(String jobOfferStatus) {
        this.jobOfferStatus = jobOfferStatus;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getPreferredSkills() {
        return preferredSkills;
    }

    public void setPreferredSkills(String preferredSkills) {
        this.preferredSkills = preferredSkills;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(Set<String> words) {
        StringJoiner joiner = new StringJoiner(",");
        words.forEach(joiner::add);
        setTags(joiner.toString());
    }

    public Set<String> getUniqueTags() {
        if (!tags.isEmpty()) {
            String[] splitted = tags.split(",");
            if (splitted.length > 0) {
                return new HashSet<>(Arrays.asList(splitted));
            }
        }
        return new HashSet<>();
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public MultipartFile getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(MultipartFile companyLogo) {
        this.companyLogo = companyLogo;
    }

    public Location getLocation() {
        return location;
    }

    public Boolean getContainsRules() {
        return containsRules;
    }

    public void setContainsRules(Boolean containsRules) {
        this.containsRules = containsRules;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Integer getThresholdPercentagePoints() {
        return thresholdPercentagePoints;
    }

    public void setThresholdPercentagePoints(Integer thresholdPercentagePoints) {
        this.thresholdPercentagePoints = thresholdPercentagePoints;
    }

    public Set<JobOfferApplication> getAllJobOfferApplications() {
        return jobOfferApplications.stream().filter(application -> !application.getPotential()).collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(JobOfferApplication::getPercentOfMaxScore).reversed())));

    }

    public Set<JobOfferApplication> getJobOfferApplications() {
        return jobOfferApplications.stream().filter(application -> !application.getPotential()).collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(JobOfferApplication::getCalculatedScore).reversed())));
    }

    public Set<JobOfferApplication> getPotentialJobOfferApplications() {
        return jobOfferApplications.stream().filter(JobOfferApplication::getPotential).collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(JobOfferApplication::getCalculatedScore).reversed())));
    }

    public Set<JobOfferApplication> getPotentialJobOfferApplicationsWithMin() {
        return jobOfferApplications.stream().filter(application -> application.getPotential() && application.getPercentOfMaxScore() > this.getThresholdPercentagePoints()).collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(JobOfferApplication::getCalculatedScore))));
    }

    public Set<JobOfferApplication> getValidJobOfferApplications() {
        return jobOfferApplications
                .stream()
                .filter(application -> application.getPercentOfMaxScore() >= this.thresholdPercentagePoints && !application.getPotential())
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(JobOfferApplication::getCalculatedScore).reversed())));
    }

    public int getNumberOfValidJobOfferApplications() {
        return jobOfferApplications.stream().filter(application -> application.getPercentOfMaxScore() >= this.thresholdPercentagePoints && !application.getPotential()).collect(Collectors.toSet()).size();
    }

    public int getNumberOfValidJobOfferApplicationsWithAcceptance() {
        return jobOfferApplications.stream().filter(application -> application.getPercentOfMaxScore() >= this.thresholdPercentagePoints && application.getEmployerAcceptancee() && !application.getPotential()).collect(Collectors.toSet()).size();
    }

    public int getNumberOfJobOfferApplicationsWithAcceptance() {
        return jobOfferApplications.stream().filter(application -> application.getEmployerAcceptancee() && !application.getPotential()).collect(Collectors.toSet()).size();
    }

    public int getNumberOfJobOfferApplicationsWithoutAcceptance() {
        return jobOfferApplications.stream().filter(application -> !application.getEmployerAcceptancee() && !application.getPotential()).collect(Collectors.toSet()).size();
    }

    public int getNumberOfValidJobOfferApplicationsWithoutAcceptance() {
        return jobOfferApplications.stream().filter(application -> application.getPercentOfMaxScore() >= this.thresholdPercentagePoints && !application.getPotential() && !application.getEmployerAcceptancee()).collect(Collectors.toSet()).size();
    }

    public int getInvalidJobOfferApplications() {
        return this.getJobOfferApplications().size() - getValidJobOfferApplications().size();
    }

    public int getValidJobOfferApplicationsNumber() {
        return this.getValidJobOfferApplications().size();
    }
    public void setJobOfferApplications(Set<JobOfferApplication> jobOfferApplications) {
        this.jobOfferApplications = jobOfferApplications;
    }

    public void addApplication(JobOfferApplication application) {
        application.setJobOffer(this);
        jobOfferApplications.add(application);
    }

    public void removeApplication(JobOfferApplication application) {
        jobOfferApplications.remove(application);
    }

    public Integer getMaximalPoints() {
        return maximalPoints;
    }

    public void setMaximalPoints(Integer maximalPoints) {
        this.maximalPoints = maximalPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOffer jobOffer = (JobOffer) o;
        return Objects.equals(jobId, jobOffer.jobId) &&
                Objects.equals(position, jobOffer.position) &&
                Objects.equals(companyName, jobOffer.companyName) &&
                Objects.equals(shortDescription, jobOffer.shortDescription) &&
                Objects.equals(description, jobOffer.description) &&
                Objects.equals(category, jobOffer.category) &&
                Objects.equals(jobOfferStatus, jobOffer.jobOfferStatus) &&
                Objects.equals(requirements, jobOffer.requirements) &&
                Objects.equals(responsibilities, jobOffer.responsibilities) &&
                Objects.equals(thresholdPercentagePoints, jobOffer.thresholdPercentagePoints) &&
                Objects.equals(preferredSkills, jobOffer.preferredSkills) &&
                Objects.equals(benefits, jobOffer.benefits) &&
                Objects.equals(additionalInfo, jobOffer.additionalInfo) &&
                Objects.equals(expirationDate, jobOffer.expirationDate) &&
                Objects.equals(tags, jobOffer.tags) &&
                Objects.equals(companyLogo, jobOffer.companyLogo) &&
                Objects.equals(maximalPoints, jobOffer.maximalPoints) &&
                Objects.equals(location, jobOffer.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, position, companyName, maximalPoints, shortDescription, description, category, jobOfferStatus, requirements, responsibilities, preferredSkills, thresholdPercentagePoints, benefits, additionalInfo, expirationDate, tags, companyLogo, location);
    }

}
