package com.ejobfinder.dao;

import com.ejobfinder.model.Employer;

import java.util.List;

public interface EmployerDao {
    void addEmployer(Employer employer);

    Employer getEmployerById(String id);

    List<Employer> getAllEmployers();

    void deleteEmployer(String id);
}
