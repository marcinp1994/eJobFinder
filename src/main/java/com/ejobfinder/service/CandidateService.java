package com.ejobfinder.service;

import com.ejobfinder.model.Candidate;

import java.util.List;

public interface CandidateService {

    void addCandidate(Candidate candidate);

    Candidate getCandidateById(int candidateId);

    List<Candidate> getAllCandidates();

    Candidate getCandidateByUsername(String username);
}
