package com.ejobfinder.dao;

import com.ejobfinder.model.PerfectEmployee;

import java.util.List;

public interface PerfectEmployeeDao {
    void addPerfectEmployee(PerfectEmployee perfectEmployee);

    PerfectEmployee getPerfectEmployeeById(int id);

    PerfectEmployee getPerfectEmployeeByJobId(String jobId);

    List<PerfectEmployee> getAllPerfectEmployees();

    void deletePerfectEmployee(int id);
}
