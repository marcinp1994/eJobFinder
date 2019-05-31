package com.ejobfinder.dao.impl;

import com.ejobfinder.dao.CandidateDao;
import com.ejobfinder.model.Authorities;
import com.ejobfinder.model.Candidate;
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
public class CandidateDaoImpl implements CandidateDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCandidate(Candidate candidate) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(candidate);

        Users newUser = new Users();
        newUser.setUsername(candidate.getUsername());
        newUser.setPassword(candidate.getPassword());
        newUser.setEnabled(true);
        newUser.setCandidateId(candidate.getCandidateId());

        Authorities newAuthority = new Authorities();
        newAuthority.setUsername(candidate.getUsername());
        newAuthority.setAuthority("ROLE_USER");

        session.saveOrUpdate(newUser);
        session.saveOrUpdate(newAuthority);

        session.flush();
    }

    public Candidate getCandidateById(int candidateId) {
        Session session = sessionFactory.getCurrentSession();
        return (Candidate) session.get(Candidate.class, candidateId);
    }

    public List<Candidate> getAllCandidates() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Candidate");
        List<Candidate> candidateList = query.list();

        return candidateList;
    }

    public Candidate getCandidateByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Candidate where username = ?");
        query.setString(0, username);

        return (Candidate) query.uniqueResult();
    }
}
