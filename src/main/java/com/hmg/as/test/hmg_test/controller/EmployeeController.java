package com.hmg.as.test.hmg_test.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hmg.as.test.hmg_test.entity.Employee;
import com.hmg.as.test.hmg_test.service.DepartmentService;
import com.hmg.as.test.hmg_test.service.EmployeeService;
import com.hmg.as.test.hmg_test.vo.EmployeeSalaryVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/employees")
@Tag(name = "Employees", description="직원 관리 API")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }
    
    @Operation(
            summary = "직원 목록 조회 API",
            description = "전체 직원 목록을 조회하는 API 입니다."
    )

    @GetMapping()
    public String viewEmployeeList(Model model) {
        List<Employee> employees              = employeeService.findAllWithDepartment();
        List<EmployeeSalaryVo> salaryRankList = employeeService.getEmployeeSalaryRank();
        model.addAttribute("employees", employees);
        model.addAttribute("salaryRankList", salaryRankList);
        return "employees"; // employees.jsp
    }

    @Operation(
            summary = "직원 조회 API",
            description = "id 값으로 직원을 조회하는 API 입니다."
    )
    @Parameters({
        @Parameter(name = "id", description = "직원의 ID", example = "3")
    })
    @ApiResponses({
        @ApiResponse(responseCode = "COM200",   description = "OK, 성공"),
        @ApiResponse(responseCode = "AUTH003",  description = "access 토큰을 주세요!",             content = @Content),
        @ApiResponse(responseCode = "AUTH004",  description = "acess 토큰 만료",                   content = @Content),
        @ApiResponse(responseCode = "AUTH006",  description = "acess 토큰 모양이 이상함",          content = @Content),
        @ApiResponse(responseCode = "EMP4001",  description = "해당 멤버가 존재하지 않음",         content = @Content),
        @ApiResponse(responseCode = "PAGE4001", description = "유효하지 않은 페이지 번호(1 미만)", content = @Content)
    })
    
    @GetMapping("/search")
    public String searchById(@RequestParam(value = "id", required = false) Long id, Model model) {
            if("".equals(id) ||  Objects.isNull(id)){
                model.addAttribute("employees", employeeService.findAllWithDepartment());
            } else {
                List<Employee> employees = employeeService.getByIdWithDepartment(id);
                if(employees.size() > 0) {
                        model.addAttribute("employees", employees);
                } else {
                    model.addAttribute("error", "해당 ID의 직원이 존재하지 않습니다.");
                    model.addAttribute("employees", employeeService.findAllWithDepartment());
                }
            }
            List<EmployeeSalaryVo> salaryRankList = employeeService.getEmployeeSalaryRank();
            model.addAttribute("salaryRankList", salaryRankList);
        return "employees";
    }

    @GetMapping("/newEmployee")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees_new";
    }
    
    // 직원 등록 처리
    @Operation(
            summary = "직원 정보 생성 API",
            description = "직원을 생성하는 API 입니다."
    )
    @Parameter(example = "employeeId=7, firstName=길동, lastName=홍, email=홍길동@naver.com, phoneNumber=010-0000-0000...")
    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee, Model model) {
        try {
            employeeService.create(employee);
            return "redirect:/employees"; // 등록 후 목록 페이지로 리다이렉트
        } catch (Exception e) {
            model.addAttribute("error", "등록 실패: " + e.getMessage());
            return "employees_new";
        }
    }
    
    @GetMapping("/{id}/detail")
    public String viewEmployeeDetail(@PathVariable Long id, Model model) {
        try {
            Employee employee = employeeService.getById(id); // id로 직원 조회
            model.addAttribute("employee", employee);
            return "employees_detail"; // employee_detail.jsp
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "해당 직원이 존재하지 않습니다.");
            return "employees"; // 다시 목록으로 리디렉션
        }
    }
    
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
        
        
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        // 직원 ID로 직원 정보를 가져오기
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAllDepartments());  // 부서 목록 추가
        return "employees_edit"; // 직원 수정 폼
    }

    @PostMapping("/{id}/update")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee, Model model) {
        try {
            // 직원 수정
            employeeService.update(id, employee);
            return "redirect:/employees"; // 수정 후 직원 목록으로 리디렉션
        } catch (Exception e) {
            model.addAttribute("error", "수정 실패: " + e.getMessage());
            model.addAttribute("departments", departmentService.getAllDepartments());  // 부서 목록 재전달
            return "employees_edit"; // 수정 실패 시 수정 폼으로 돌아감
        }
    }

    @Operation(
            summary = "직원 정보 삭제 API",
            description = "직원 정보를 삭제하는 API 입니다."
    )
    @PostMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.delete(id); // 직원 삭제
        } catch (Exception e) {
            return "redirect:/employees?error=" + e.getMessage(); // 오류 처리
        }
        return "redirect:/employees"; // 삭제 후 직원 목록으로 리디렉션
    }
    
    @PostMapping("/{employeeId}/updateJob")
    public String updateJob(@PathVariable Long employeeId) {
        // 직원별로 저장 프로시저 호출
        employeeService.updateEmployeeJob(employeeId);
        return "redirect:/employees"; // 업데이트 후 직원 목록 페이지로 리다이렉트
    }
}