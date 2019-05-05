package com.ejobfinder.dao;

import com.ejobfinder.model.JobOffer;

import java.util.List;

public interface JobOfferDao {

    void addJobOffer(JobOffer jobOffer);

    JobOffer getJobOfferById(String id);

    List<JobOffer> getAllJobOffers();

    void deleteJobOffer(String id);

    List<JobOffer>  getJobOffersByEmployerId(String employerId);
}
