package com.ejobfinder.dao.impl;

import com.ejobfinder.dao.PerfectEmployeeDao;
import com.ejobfinder.model.PerfectEmployee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PerfectEmployeeDaoImpl implements PerfectEmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPerfectEmployee(PerfectEmployee perfectEmployee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(perfectEmployee);
        session.flush();
    }

    @Override
    public PerfectEmployee getPerfectEmployeeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        PerfectEmployee perfectEmployee = (PerfectEmployee) session.get(PerfectEmployee.class, id);
        session.flush();

        return perfectEmployee;
    }

    @Override
    public PerfectEmployee getPerfectEmployeeByJobId(String jobId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = sessionFactory.getCurrentSession().
                createQuery("from PerfectEmployee where jobId=:jobId");
        query.setParameter("jobId", jobId);
        PerfectEmployee perfectEmployee = (PerfectEmployee) query.uniqueResult();
        session.flush();

        return perfectEmployee;
    }

    @Override
    public List<PerfectEmployee> getAllPerfectEmployees() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Location");
        List<PerfectEmployee> perfectEmployeeList = query.list();
        session.flush();
        return perfectEmployeeList;
    }

    @Override
    public void deletePerfectEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getPerfectEmployeeById(id));
        session.flush();
    }
}
