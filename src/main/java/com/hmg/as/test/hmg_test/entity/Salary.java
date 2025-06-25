package com.hmg.as.test.hmg_test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;

    private Double baseSalary;
    private Double bonus;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;  // 급여는 직원과 1:N 관계
}