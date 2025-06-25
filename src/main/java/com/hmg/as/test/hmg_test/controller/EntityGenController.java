package com.hmg.as.test.hmg_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hmg.as.test.hmg_test.service.EntityGeneratorService;

@Controller
public class EntityGenController {

    @Autowired
    private EntityGeneratorService generatorService;

    @PostMapping("/generate")
    public String generateEntity(@RequestParam String tableName, Model model) {
        String entityCode = generatorService.generateEntityFromTable(tableName);
        model.addAttribute("entityCode", entityCode);
        return "entityGen";
    }

    @GetMapping("/")
    public String index() {
        return "entityGen";
    }
}