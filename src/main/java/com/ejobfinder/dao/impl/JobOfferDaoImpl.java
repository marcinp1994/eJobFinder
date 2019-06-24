package com.ejobfinder.dao.impl;

import com.ejobfinder.dao.JobOfferDao;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.Location;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
@Transactional
public class JobOfferDaoImpl implements JobOfferDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String addJobOffer(JobOffer jobOffer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(jobOffer);
        session.flush();
        return jobOffer.getJobId();
    }

    @Override
    public String addJobOfferWithCandidate(JobOffer jobOffer) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(jobOffer);

        session.flush();
        session.clear();
        return jobOffer.getJobId();
    }

    @Override
    public String editJobOffer(JobOffer jobOffer) {
        Session session = sessionFactory.getCurrentSession();
        session.evict(jobOffer);
        session.saveOrUpdate(jobOffer);
        session.flush();
        return jobOffer.getJobId();
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
        JobOffer jobOffer = getJobOfferById(id);
        Location location = jobOffer.getLocation();

        for (Iterator<JobOffer> i = location.getJobOffers().iterator(); i.hasNext(); ) {
            JobOffer jobOffer1 = i.next();
            if (jobOffer.equals(jobOffer1)) {
                i.remove();
            }
        }
        jobOffer.setLocation(null);
        session.saveOrUpdate(location);
        session.delete(getJobOfferById(id));
        session.flush();
    }

    @Override
    public List<JobOffer> getJobOffersByEmployerName(String employerName) {
        List<JobOffer> jobOffersFromSpecificEmployer = null;
        for(JobOffer jobOffer : getAllJobOffers()){
            if (jobOffer.getEmployer().getUsername().equals(employerName)) {
                if(jobOffersFromSpecificEmployer == null){
                    jobOffersFromSpecificEmployer = new ArrayList<>();
                }
                jobOffersFromSpecificEmployer.add(jobOffer);
            }
        }

        return jobOffersFromSpecificEmployer;
    }

    @Override
    public List<JobOffer> getJobOffersByEmployerId(int id) {
        List<JobOffer> jobOffersFromSpecificCustomer = null;
        for (JobOffer jobOffer : getAllJobOffers()) {
            if (jobOffer.getEmployer().getEmployerId() == id) {
                if (jobOffersFromSpecificCustomer == null) {
                    jobOffersFromSpecificCustomer = new ArrayList<>();
                }
                jobOffersFromSpecificCustomer.add(jobOffer);
            }
        }

        return jobOffersFromSpecificCustomer;
    }

    @Override
    public List<JobOffer> getJobOffersByCategory(String category) {
        List<JobOffer> jobOffersForCategory = null;
        for (JobOffer jobOffer : getAllJobOffers()) {
            if (category.equalsIgnoreCase(jobOffer.getCategory())) {
                if (jobOffersForCategory == null) {
                    jobOffersForCategory = new ArrayList<>();
                }
                jobOffersForCategory.add(jobOffer);
            }
        }
        return jobOffersForCategory;
    }
}
