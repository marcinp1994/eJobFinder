package com.ejobfinder.dao;

import com.ejobfinder.model.Candidate;

import java.util.List;

public interface CandidateDao {

    void addCandidate(Candidate candidate);

    void updateCandidate(Candidate candidate);

    Candidate getCandidateById(int candidateId);

    List<Candidate> getAllCandidates();

    Candidate getCandidateByUsername(String username);

}
