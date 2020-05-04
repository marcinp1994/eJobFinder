package com.ejobfinder.service.impl;

import com.ejobfinder.dao.JobOfferDao;
import com.ejobfinder.model.Candidate;
import com.ejobfinder.model.JobOffer;
import com.ejobfinder.model.JobOfferApplication;
import com.ejobfinder.service.JobOfferService;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JobOfferServiceImpl implements JobOfferService {

    private JobOfferDao jobOfferDao;

    public JobOfferServiceImpl(JobOfferDao jobOfferDao) {
        this.jobOfferDao = jobOfferDao;
    }

    @Override
    public String addJobOffer(JobOffer jobOffer) {
        return jobOfferDao.addJobOffer(jobOffer);
    }

    @Override
    public String editJobOffer(JobOffer jobOffer) {
        return jobOfferDao.editJobOffer(jobOffer);
    }

    @Override
    public void updateJobOffer(JobOffer jobOffer) {
        jobOfferDao.updateJobOffer(jobOffer);
    }

    @Override
    public JobOffer getJobOfferById(String jobId) {
        return jobOfferDao.getJobOfferById(jobId);
    }

    @Override
    public List<JobOffer> getAllJobOffers() {
        return jobOfferDao.getAllJobOffers();
    }

    @Override
    public void deleteJobOffer(String jobId) {
        jobOfferDao.deleteJobOffer(jobId);
    }

    @Override
    public List<JobOffer> getJobOffersByEmployerName(String name) {
        return jobOfferDao.getJobOffersByEmployerName(name);
    }

    @Override
    public List<JobOffer> getJobOffersByCategory(String category) {
        return jobOfferDao.getJobOffersByCategory(category);
    }

    @Override
    public List<JobOffer> getJobOffersByEmployerId(int id) {
        return jobOfferDao.getJobOffersByEmployerId(id);
    }

    @Override
    public void matchTagsWithCandidateCV(JobOffer offer, Candidate candidate, JobOfferApplication application) {
        Set<String> offerTags = offer.getUniqueTags();
        if (!offerTags.isEmpty()) {
            Set<String> matchedKeyWords = getMatchingKeyWordsFromCV(offerTags, candidate);
            Double percentOfMatchedKeyWords = (double) (matchedKeyWords.size() * 100 / offerTags.size());
            application.setPercentOfMatchedKeyWords(percentOfMatchedKeyWords);
            application.setMatchedKeyWords(matchedKeyWords);
        }
    }

    private Set<String> getMatchingKeyWordsFromCV(Set<String> offerTags, Candidate candidate) {
        Set<String> matchedKeyWords = new HashSet<>();
        if (candidate.getCvFIle() != null && candidate.getCvFIle().length > 0) {
            InputStream is = convertBytesToInputStream(candidate.getCvFIle());
            if (is != null) try {
                PDDocument document = PDDocument.load(is);
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String cvText = pdfStripper.getText(document);
                matchWords(matchedKeyWords, offerTags, cvText);
                document.close();
            } catch (IOException ignored) {
            }
        }
        return matchedKeyWords;
    }

    private static void matchWords(Set<String> matchedKeyWords, Set<String> jobKeyWords, String cvText) {
        if (!jobKeyWords.isEmpty()) {
            for (String key : jobKeyWords) {
                if (StringUtils.containsIgnoreCase(cvText, key)) {
                    matchedKeyWords.add(key);
                }
            }
        }
    }

    private InputStream convertBytesToInputStream(byte[] content) {
        InputStream is = null;
        if (content != null) {
            int size = content.length;
            byte[] b = new byte[size];
            is = new ByteArrayInputStream(content);
        }
        return is;
    }
}
