package com.ejobfinder.service;

import com.ejobfinder.model.JobOffer;

import java.util.List;

public interface JobOfferService {

    String addJobOffer(JobOffer jobOffer);

    String addJobOfferWithCandidate(JobOffer jobOffer);

    String editJobOffer(JobOffer jobOffer);

    JobOffer getJobOfferById(String jobId);

    List<JobOffer> getAllJobOffers();

    void deleteJobOffer(String jobId);

    List<JobOffer> getJobOffersByEmployerName(String name);

    List<JobOffer> getJobOffersByCategory(String category);

    List<JobOffer> getJobOffersByEmployerId(int id);
}
