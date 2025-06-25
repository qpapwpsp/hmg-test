package com.hmg.as.test.hmg_test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmg.as.test.hmg_test.service.PostgresEntityGeneratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/")
@Tag(name = "Entity자동 생성 API", description = "해당 Table Entity자동 생성 API")
public class PostgresEntityGeneratorController {
	private final PostgresEntityGeneratorService postgresEntityGeneratorService;

    public PostgresEntityGeneratorController(PostgresEntityGeneratorService postgresEntityGeneratorService) {
        this.postgresEntityGeneratorService = postgresEntityGeneratorService;
    }
	@GetMapping("/generateEntity/{tableName}")
    @Operation(summary = "해당 테이블의 Entity template 자동생성", description = "해당 테이블의 Entity template를 자동생성한다.")
    public ResponseEntity<String> createEntityTemplate(@PathVariable String tableName)  {              
        
        try {
        	postgresEntityGeneratorService.generateEntity(tableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body("❌ 오류: " + e.getMessage());
		}        
        return ResponseEntity.ok("✅ Entity template 생성 완료: " + tableName);
        
    }    
}
