package com.ejobfinder.dao.impl;

import com.ejobfinder.dao.EmployerDao;
import com.ejobfinder.model.Employer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EmployerDaoImpl implements EmployerDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addEmployer(Employer employer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employer);
        session.flush();
    }

    @Override
    public Employer getEmployerByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Employer employer = (Employer) session.get(Employer.class, name);
        session.flush();

        return employer;
    }

    @Override
    public List<Employer> getAllEmployers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Employer");
        List<Employer> employerList = query.list();
        session.flush();
        return employerList;
    }

    @Override
    public void deleteEmployer(String id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getEmployerByName(id));
        session.flush();
    }
}
