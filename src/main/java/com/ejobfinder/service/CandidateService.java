package com.ejobfinder.service;

import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.facts.CandidateFacts;

import java.util.List;

public interface CandidateService {

    void addCandidate(Candidate candidate);

    void updateCandidate(Candidate candidate);

    Candidate getCandidateById(int candidateId);

    List<Candidate> getAllCandidates();

    Candidate getCandidateByUsername(String username);

    Integer evaluateScoringOnJobOffer(String jobOfferId, CandidateFacts factsAboutUser, Candidate candidate);

    Integer evaluateScoringOnJobOffer(String jobOfferId, Candidate candidate);

}
