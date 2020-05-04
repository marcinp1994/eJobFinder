package com.ejobfinder.service;

import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.JobOfferApplication;

import java.util.List;

public interface JobOfferService {

    String addJobOffer(JobOffer jobOffer);

    String editJobOffer(JobOffer jobOffer);

    void updateJobOffer(JobOffer jobOffer);

    JobOffer getJobOfferById(String jobId);

    List<JobOffer> getAllJobOffers();

    void deleteJobOffer(String jobId);

    List<JobOffer> getJobOffersByEmployerName(String name);

    List<JobOffer> getJobOffersByCategory(String category);

    List<JobOffer> getJobOffersByEmployerId(int id);

    void matchTagsWithCandidateCV(JobOffer offer, Candidate candidate, JobOfferApplication application);
}