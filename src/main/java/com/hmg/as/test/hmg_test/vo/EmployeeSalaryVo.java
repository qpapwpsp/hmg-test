package com.hmg.as.test.hmg_test.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryVo {
    private Long employeeId;
    private String employeeName;
    private Long totalSalary;
}