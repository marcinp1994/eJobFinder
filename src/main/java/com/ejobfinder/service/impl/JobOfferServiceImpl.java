package com.ejobfinder.service.impl;

import com.ejobfinder.dao.JobOfferDao;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOfferServiceImpl implements JobOfferService {

    @Autowired
    private JobOfferDao jobOfferDao;

    @Override
    public String addJobOffer(JobOffer jobOffer) {
        String id = jobOfferDao.addJobOffer(jobOffer);
        return id;
    }

    @Override
    public String addJobOfferWithCandidate(JobOffer jobOffer) {
        String id = jobOfferDao.addJobOfferWithCandidate(jobOffer);
        return id;
    }

    @Override
    public String editJobOffer(JobOffer jobOffer) {
        String id = jobOfferDao.editJobOffer(jobOffer);
        return id;
    }

    @Override
    public JobOffer getJobOfferById(String jobId) {
        return jobOfferDao.getJobOfferById(jobId);
    }

    @Override
    public List<JobOffer> getAllJobOffers() {
        return jobOfferDao.getAllJobOffers();
    }

    @Override
    public void deleteJobOffer(String jobId) {
        jobOfferDao.deleteJobOffer(jobId);
    }

    @Override
    public List<JobOffer> getJobOffersByEmployerName(String name) {
        return jobOfferDao.getJobOffersByEmployerName(name);
    }

    @Override
    public List<JobOffer> getJobOffersByCategory(String category) {
        return jobOfferDao.getJobOffersByCategory(category);
    }

    @Override
    public List<JobOffer> getJobOffersByEmployerId(int id) {
        return jobOfferDao.getJobOffersByEmployerId(id);
    }
}
