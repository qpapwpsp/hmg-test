package com.hmg.as.test.hmg_test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hmg.as.test.hmg_test.entity.Department;
import com.hmg.as.test.hmg_test.repository.DepartmentRepository;

//DepartmentServiceImpl.java
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

     @Override
     public List<Department> getAllDepartments() {
         return departmentRepository.findAll();
     }
}
