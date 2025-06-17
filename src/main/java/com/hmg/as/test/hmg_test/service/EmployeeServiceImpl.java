package com.hmg.as.test.hmg_test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hmg.as.test.hmg_test.entity.Employee;
import com.hmg.as.test.hmg_test.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
    private final EmployeeRepository employeeRepository;
    
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 직원이 존재하지 않습니다. ID=" + id));
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee target = getById(id);
//        target.setFirstName(employee.getFirstName());
//        target.setLastName(employee.getLastName());
//        target.setEmail(employee.getEmail());
//        target.setPhoneNumber(employee.getPhoneNumber());
//        target.setHireDate(employee.getHireDate());
//        target.setJobTitle(employee.getJobTitle());
//        target.setDepartmentId(employee.getDepartmentId());
//        target.setIsActive(employee.getIsActive());
        return employeeRepository.save(target);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
