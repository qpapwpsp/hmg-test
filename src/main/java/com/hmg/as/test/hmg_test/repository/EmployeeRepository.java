package com.hmg.as.test.hmg_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.hmg.as.test.hmg_test.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Optional: 메소드 추가 예시
    Employee findByEmail(String email);
    
    @Query("SELECT e "
      	  +  "FROM Employee e "
          +  "JOIN FETCH e.department "
      	  +  "ORDER BY e.employeeId DESC")
    List<Employee> findAllWithDepartment();
    
    @Query("SELECT e "
        + "   FROM Employee e "
    		+ "   JOIN FETCH e.department"
    		+ "  WHERE e.employeeId = :id"
        + "  ORDER BY e.employeeId DESC")
    List<Employee> findByIdWithDepartment(@Param("id") Long id);

    @Procedure("public.update_employee_department_job")
    void updateEmployeeDepartmentJob(@Param("employee_id") int employeeId);
}