package com.ejobfinder.dao.impl;

import com.ejobfinder.dao.JobOfferDao;
import com.ejobfinder.model.JobOffer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class JobOfferDaoImpl implements JobOfferDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addJobOffer(JobOffer jobOffer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(jobOffer);
        session.flush();
    }

    @Override
    public JobOffer getJobOfferById(String id) {
        Session session = sessionFactory.getCurrentSession();
        JobOffer jobOffer = (JobOffer) session.get(JobOffer.class, id);
        session.flush();

        return jobOffer;
    }

    @Override
    public List<JobOffer> getAllJobOffers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from JobOffer");
        List<JobOffer> jobOffers = query.list();
        session.flush();
        return jobOffers;
    }

    @Override
    public void deleteJobOffer(String id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getJobOfferById(id));
        session.flush();
    }

    @Override
    public List<JobOffer> getJobOffersByEmployerId(String employerId) {
        List<JobOffer> jobOffersFromSpecificEmployer = null;
        for(JobOffer jobOffer : getAllJobOffers()){
            if(jobOffer.getEmployer().getEmployerId().equals(employerId)){
                if(jobOffersFromSpecificEmployer == null){
                    jobOffersFromSpecificEmployer = new ArrayList<>();
                }
                jobOffersFromSpecificEmployer.add(jobOffer);
            }
        }

        return jobOffersFromSpecificEmployer;
    }
}
