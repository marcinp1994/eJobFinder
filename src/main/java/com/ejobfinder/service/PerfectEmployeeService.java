package com.ejobfinder.service;

import com.ejobfinder.model.PerfectEmployee;

import java.util.List;

public interface PerfectEmployeeService {
    void addPerfectEmployee(PerfectEmployee perfectEmployee);

    PerfectEmployee getPerfectEmployeeById(int id);

    PerfectEmployee getPerfectEmployeeByJobId(String jobId);

    List<PerfectEmployee> getAllPerfectEmployees();

    void deletePerfectEmployee(int id);
}
