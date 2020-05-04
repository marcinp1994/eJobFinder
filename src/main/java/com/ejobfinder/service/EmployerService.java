package com.ejobfinder.service;

import com.ejobfinder.model.Employer;

import java.util.List;

public interface EmployerService {

    void addEmployer(Employer employer);

    Employer getEmployerById(int employerId);

    List<Employer> getAllEmployers();

    Employer getEmployerByUsername(String username);

    void updateEmployer(Employer employer);
}
