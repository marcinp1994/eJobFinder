package com.ejobfinder.dao.impl;

import com.ejobfinder.dao.EmployerDao;
import com.ejobfinder.model.Authorities;
import com.ejobfinder.model.Employer;
import com.ejobfinder.model.Users;
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

    private SessionFactory sessionFactory;

    public EmployerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addEmployer(Employer employer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employer);

        Users newUser = new Users();
        newUser.setUsername(employer.getUsername());
        newUser.setPassword(employer.getPassword());
        newUser.setEnabled(true);
        newUser.setEmployerId(employer.getEmployerId());

        Authorities newAuthority = new Authorities();
        newAuthority.setUsername(employer.getUsername());
        newAuthority.setAuthority("ROLE_EMPLOYER");

        session.saveOrUpdate(newUser);
        session.saveOrUpdate(newAuthority);

        session.flush();
    }

    @Override
    public Employer updateEmployer(Employer employer) {
        Session session = sessionFactory.getCurrentSession();
        session.evict(employer);
        session.saveOrUpdate(employer);
        session.flush();
        return employer;
    }
    public Employer getEmployerById(int employerId) {
        Session session = sessionFactory.getCurrentSession();
        return (Employer) session.get(Employer.class, employerId);
    }

    public List<Employer> getAllEmployers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Employer");

        return (List<Employer>) query.list();
    }

    public Employer getEmployerByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Employer where username = ?");
        query.setString(0, username);

        return (Employer) query.uniqueResult();
    }
}
