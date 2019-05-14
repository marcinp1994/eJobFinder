package com.ejobfinder.service;

import com.ejobfinder.model.JobOffer;

import java.util.List;

/**
 * Created by Le on 1/25/2016.
 */
public interface JobOfferService {

    void addJobOffer(JobOffer jobOffer);

    void editJobOffer(JobOffer jobOffer);

    JobOffer getJobOfferById(String jobId);

    List<JobOffer> getAllJobOffers();

    void deleteJobOffer(String jobId);

    List<JobOffer> getJobOffersByCustomerName(String name);

    List<JobOffer> getJobOffersByCategory(String category);

    List<JobOffer> getJobOffersByCustomerId(int id);
}
