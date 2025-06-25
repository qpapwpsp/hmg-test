package com.hmg.as.test.hmg_test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hmg.as.test.hmg_test.entity.Employee;

@Mapper
public interface EmployeeMapper {
    String findById(Long id);
    List<Employee> findAll();
    void updateJobtTtle(Long id);
}