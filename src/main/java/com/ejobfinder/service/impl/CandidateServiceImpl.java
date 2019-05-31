package com.ejobfinder.service.impl;

import com.ejobfinder.dao.CandidateDao;
import com.ejobfinder.model.Candidate;
import com.ejobfinder.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateDao candidateDao;

    public void addCandidate(Candidate candidate) {
        candidateDao.addCandidate(candidate);
    }

    @Override
    public void updateCandidate(Candidate candidate) {
        candidateDao.updateCandidate(candidate);
    }

    public Candidate getCandidateById(int candidateId) {
        return candidateDao.getCandidateById(candidateId);
    }

    public List<Candidate> getAllCandidates() {
        return candidateDao.getAllCandidates();
    }

    public Candidate getCandidateByUsername(String username) {
        return candidateDao.getCandidateByUsername(username);
    }
}
