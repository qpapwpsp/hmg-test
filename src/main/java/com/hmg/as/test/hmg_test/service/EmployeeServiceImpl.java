package com.hmg.as.test.hmg_test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hmg.as.test.hmg_test.entity.Department;
import com.hmg.as.test.hmg_test.entity.Employee;
import com.hmg.as.test.hmg_test.entity.QEmployee;
import com.hmg.as.test.hmg_test.entity.QSalary;
import com.hmg.as.test.hmg_test.mapper.EmployeeMapper;
import com.hmg.as.test.hmg_test.repository.DepartmentRepository;
import com.hmg.as.test.hmg_test.repository.EmployeeRepository;
import com.hmg.as.test.hmg_test.vo.EmployeeSalaryVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final EmployeeMapper employeeMapper;
    private final JPAQueryFactory queryFactory;
    
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, EmployeeMapper employeeMapper, JPAQueryFactory queryFactory) {
        this.employeeRepository   = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapper       = employeeMapper;
        this.queryFactory = queryFactory;
    }
    
    @Override
    public List<Employee> findAllWithDepartment() {
        // 01. JPQL 사용예시
//        String jpql = "SELECT e FROM Employee e JOIN FETCH e.department ORDER BY e.employeeId DESC";
//        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
//        return query.getResultList();
        
        // 02. 네이티브 SQL 사용예시 -> DEPARTMENTS 테이블 EMPLOYEES 로우수 만큼 조회됨(해결방법 아직 모름)
//        String sql  = "SELECT E.* "
//                + "      FROM EMPLOYEES E "
//                + "     INNER JOIN DEPARTMENTS D "
//                + "        ON E.DEPARTMENT_ID = D.DEPARTMENT_ID " 
//                + "     ORDER BY E.EMPLOYEE_ID DESC";
//        Query query = entityManager.createNativeQuery(sql, Employee.class);
//        return query.getResultList();
        
        // 03. JPA레파지토리에서 @Qurey 어노테이션 사용예시
        String frstName = this.employeeMapper.findById(1L);
    	    return employeeRepository.findAllWithDepartment();
    }
    
    @Override
    public List<Employee> getByIdWithDepartment(Long id) {
        List<Employee> employee = employeeRepository.findByIdWithDepartment(id);
        return employee;
    }
    
    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    @Override
    public Employee update(Long id, Employee employee) {
        Employee target = getById(id);  // 해당 직원 정보 가져오기
        // 수정할 필드들
        target.setFirstName(employee.getFirstName());
        target.setLastName(employee.getLastName());
        target.setEmail(employee.getEmail());
        target.setPhoneNumber(employee.getPhoneNumber());
        target.setHireDate(employee.getHireDate());
        target.setJobTitle(employee.getJobTitle());
        target.setIsActive(employee.getIsActive());

        // 부서 정보 업데이트
        if (employee.getDepartment() != null && employee.getDepartment().getDepartmentId() != null) {
            Department dept = departmentRepository.findById(employee.getDepartment().getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("부서 정보 없음"));
            target.setDepartment(dept);
        }

        // 수정된 직원 정보를 저장
        Employee rtn = employeeRepository.save(target);
        this.employeeMapper.updateJobtTtle(1L);
        
        return rtn;
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 직원이 존재하지 않습니다. ID=" + id));
    }
    
    // 프로시저 호출 시 @Transactional 어노테이션 붙여야함
    @Transactional
    @Override
    public void updateEmployeeJob(Long employeeId) {
        //  프로시저 호출
        employeeRepository.updateEmployeeDepartmentJob(employeeId.intValue()); // employeeId는 int로 전달
    }

    @Override
    public List<EmployeeSalaryVo> getEmployeeSalaryRank() {
        QEmployee employee = QEmployee.employee;
        QSalary salary = QSalary.salary;

        return queryFactory
                .select(Projections.constructor(
                        EmployeeSalaryVo.class,
                        employee.employeeId,
                        employee.lastName.concat(employee.firstName),
                        salary.baseSalary.add(salary.bonus).sum().longValue()
                ))
                .from(employee)
                .join(salary).on(employee.employeeId.eq(salary.employee.employeeId))
                .groupBy(employee.employeeId, employee.firstName, employee.lastName)
                .orderBy(salary.baseSalary.add(salary.bonus).sum().desc())
                .fetch();
    }
}