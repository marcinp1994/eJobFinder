package com.ejobfinder.dao;

import com.ejobfinder.model.Employer;

import java.util.List;

public interface EmployerDao {

    void addEmployer(Employer employer);

    Employer updateEmployer(Employer employer);

    Employer getEmployerById(int employerId);

    List<Employer> getAllEmployers();

    Employer getEmployerByUsername(String username);

}
