package com.hmg.as.test.hmg_test.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hmg.as.test.hmg_test.entity.Employee;

public interface EmployeeService {
	// 전체조회
    List<Employee> findAllWithDepartment();
    // 검색
    List<Employee> getByIdWithDepartment(Long id);
    // 등록
    Employee create(Employee employee);
    // 수정
    Employee update(Long id, Employee employee);
    // 삭제
    void delete(Long id);
    // ID조회
    Employee getById(Long id);
    // 프로시저호출
    void updateEmployeeJob(Long employeeId);
}