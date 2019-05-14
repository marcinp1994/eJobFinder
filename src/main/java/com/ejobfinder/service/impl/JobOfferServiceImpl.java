package com.ejobfinder.service.impl;

import com.ejobfinder.dao.JobOfferDao;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Le on 1/25/2016.
 */

@Service
public class JobOfferServiceImpl implements JobOfferService {

    @Autowired
    private JobOfferDao jobOfferDao;

    @Override
    public void addJobOffer(JobOffer jobOffer) {
        jobOfferDao.addJobOffer(jobOffer);
    }

    @Override
    public void editJobOffer(JobOffer jobOffer) {
        jobOfferDao.editJobOffer(jobOffer);
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
    public List<JobOffer> getJobOffersByCustomerName(String name) {
        return jobOfferDao.getJobOffersByCustomerName(name);
    }

    @Override
    public List<JobOffer> getJobOffersByCategory(String category) {
        return jobOfferDao.getJobOffersByCategory(category);
    }

    @Override
    public List<JobOffer> getJobOffersByCustomerId(int id) {
        return jobOfferDao.getJobOffersByCustomerId(id);
    }
}
