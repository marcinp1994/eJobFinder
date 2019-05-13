package com.ejobfinder.dao;

import com.ejobfinder.model.Employer;

import java.util.List;

public interface EmployerDao {
    void addEmployer(Employer employer);

    Employer getEmployerByName(String name);

    List<Employer> getAllEmployers();

    void deleteEmployer(String id);
}
