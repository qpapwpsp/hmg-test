package com.hmg.as.test.hmg_test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hmg.as.test.hmg_test.entity.Employee;
import com.hmg.as.test.hmg_test.service.EmployeeService;

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

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "직원 목록 조회 API",
            description = "전체 직원 목록을 조회하는 API 입니다."
    )

    @GetMapping()
    public String viewEmployeeList(Model model) {
        List<Employee> employees = employeeService.getAll();
        
        model.addAttribute("employees", employees);
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
        @ApiResponse(responseCode = "AUTH003",  description = "access 토큰을 주세요!",         content = @Content),
        @ApiResponse(responseCode = "AUTH004",  description = "acess 토큰 만료",              content = @Content),
        @ApiResponse(responseCode = "AUTH006",  description = "acess 토큰 모양이 이상함",       content = @Content),
        @ApiResponse(responseCode = "EMP4001",  description = "해당 멤버가 존재하지 않음",        content = @Content),
        @ApiResponse(responseCode = "PAGE4001", description = "유효하지 않은 페이지 번호(1 미만)", content = @Content)
    })
    @GetMapping("/search")
    public String searchById(@RequestParam("id") Long id, Model model) {
        try {
            Employee employee = employeeService.getById(id);
            model.addAttribute("employee", employee);
            model.addAttribute("employees", employeeService.getAll());
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "해당 ID의 직원이 존재하지 않습니다.");
            model.addAttribute("employees", employeeService.getAll());
        }
        return "employees";
    }

    @GetMapping("/newEmployee")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees_new";
    }
    
    // 직원 등록 처리
    @PostMapping("/create")
    public String create(@RequestBody Employee employee) {
    	return "redirect:/employees";
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
            return "employee_new";
        }
    }

    @Operation(
            summary = "직원 정보 수정 API",
            description = "직원 정보를 수정하는 API 입니다."
    )
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @Operation(
            summary = "직원 정보 삭제 API",
            description = "직원 정보를 삭제하는 API 입니다."
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}