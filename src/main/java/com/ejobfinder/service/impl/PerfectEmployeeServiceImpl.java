package com.ejobfinder.service.impl;

import com.ejobfinder.dao.PerfectEmployeeDao;
import com.ejobfinder.model.PerfectEmployee;
import com.ejobfinder.service.PerfectEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfectEmployeeServiceImpl implements PerfectEmployeeService {

    @Autowired
    private PerfectEmployeeDao perfectEmployeeDao;

    @Override
    public void addPerfectEmployee(PerfectEmployee perfectEmployee) {
        perfectEmployeeDao.addPerfectEmployee(perfectEmployee);
    }

    @Override
    public PerfectEmployee getPerfectEmployeeById(int id) {
        return perfectEmployeeDao.getPerfectEmployeeById(id);
    }

    @Override
    public PerfectEmployee getPerfectEmployeeByJobId(String jobId) {
        return perfectEmployeeDao.getPerfectEmployeeByJobId(jobId);
    }

    @Override
    public List<PerfectEmployee> getAllPerfectEmployees() {
        return perfectEmployeeDao.getAllPerfectEmployees();
    }

    @Override
    public void deletePerfectEmployee(int id) {
        perfectEmployeeDao.deletePerfectEmployee(id);
    }
}
