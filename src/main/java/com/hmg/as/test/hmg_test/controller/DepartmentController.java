package com.hmg.as.test.hmg_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hmg.as.test.hmg_test.service.DepartmentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/department")
@Tag(name = "Departments", description="부서 관리 API")
public class DepartmentController {
    
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}