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

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping()
    public String viewEmployeeList(Model model) {
        List<Employee> employees = employeeService.getAll();
        
        model.addAttribute("employees", employees);
        return "employees"; // employees.jsp
    }
    
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


    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}