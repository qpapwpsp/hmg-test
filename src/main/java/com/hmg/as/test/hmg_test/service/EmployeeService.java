package com.hmg.as.test.hmg_test.service;

import java.util.List;

import com.hmg.as.test.hmg_test.entity.Employee;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(Long id);
    Employee create(Employee employee);
    Employee update(Long id, Employee employee);
    void delete(Long id);
}
