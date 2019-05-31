package com.ejobfinder.service.impl;

import com.ejobfinder.dao.EmployerDao;
import com.ejobfinder.model.Employer;
import com.ejobfinder.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerDao employerDao;

    public void addEmployer(Employer employer) {
        employerDao.addEmployer(employer);
    }

    public Employer getEmployerById(int employerId) {
        return employerDao.getEmployerById(employerId);
    }

    public List<Employer> getAllEmployers() {
        return employerDao.getAllEmployers();
    }

    public Employer getEmployerByUsername(String username) {
        return employerDao.getEmployerByUsername(username);
    }
}
