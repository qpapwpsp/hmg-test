package com.hmg.as.test.hmg_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hmg.as.test.hmg_test.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Optional: 메소드 추가 예시
    Employee findByEmail(String email);
}