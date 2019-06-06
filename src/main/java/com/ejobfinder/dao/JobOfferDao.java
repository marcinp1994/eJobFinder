package com.ejobfinder.dao;

import com.ejobfinder.model.JobOffer;

import java.util.List;

public interface JobOfferDao {

    void addJobOffer(JobOffer jobOffer);

    void addJobOfferWithCandidate(JobOffer jobOffer);

    void editJobOffer(JobOffer jobOffer);

    JobOffer getJobOfferById(String id);

    List<JobOffer> getAllJobOffers();

    void deleteJobOffer(String id);

    List<JobOffer> getJobOffersByEmployerName(String name);

    List<JobOffer> getJobOffersByEmployerId(int id);

    List<JobOffer> getJobOffersByCategory(String category);
}
