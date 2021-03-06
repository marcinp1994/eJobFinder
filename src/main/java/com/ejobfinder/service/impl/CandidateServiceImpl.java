package com.ejobfinder.service.impl;

import com.ejobfinder.dao.CandidateDao;
import com.ejobfinder.drools.utils.DroolsUtility;
import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.facts.CandidateFacts;
import com.ejobfinder.service.CandidateService;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateDao candidateDao;
    private DroolsUtility droolsUtility;

    @Autowired
    public CandidateServiceImpl(CandidateDao candidateDao, DroolsUtility droolsUtility) {
        this.candidateDao = candidateDao;
        this.droolsUtility = droolsUtility;
    }

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


    @Override
    public Integer evaluateScoringOnJobOffer(String jobOfferId, Candidate candidate) {
        StatelessKieSession session = droolsUtility.loadSession(jobOfferId);
        candidate.setScore(0);
        session.setGlobal("candidate", candidate);

        session.execute(candidate.getTechnologyFacts());
        session.execute(candidate.getTypeOfContractFacts());
        session.execute(candidate.getEducationFacts());
        session.execute(candidate.getLanguageFacts());
        session.execute(candidate.getLocationFacts());
        session.execute(candidate.getPeriodOfNoticeFacts());
        session.execute(candidate.getPreviousEmployerFacts());
        session.execute(candidate.getSalaryFacts());
        session.execute(candidate.getToolFacts());
        session.execute(candidate.getWorkingHoursFacts());
        session.execute(candidate.getSkillFacts());

        return candidate.getScore();
    }


}
