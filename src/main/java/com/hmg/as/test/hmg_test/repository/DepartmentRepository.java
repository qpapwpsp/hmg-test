package com.hmg.as.test.hmg_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hmg.as.test.hmg_test.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}