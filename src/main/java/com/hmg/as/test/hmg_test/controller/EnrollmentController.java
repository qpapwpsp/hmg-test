package com.hmg.as.test.hmg_test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmg.as.test.hmg_test.service.EnrollmentService;
import com.hmg.as.test.hmg_test.vo.CourseVo;
import com.hmg.as.test.hmg_test.vo.EnrollVo;
import com.hmg.as.test.hmg_test.vo.ProfessorVo;
import com.hmg.as.test.hmg_test.vo.StudentVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/")
@Tag(name = "수강 API", description = "수강 등록 및 조회 관련 API")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/{studentId}/courses")
    @Operation(summary = "수강 과목 조회", description = "학생 ID로 수강 중인 과목 목록을 조회합니다")
    public ResponseEntity<List<EnrollVo>> getCoursesByStudentId(@PathVariable Long studentId) {
        List<EnrollVo> result = enrollmentService.getCoursesByStudentId(studentId);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/{courseId}")
    @Operation(summary = "과목정보 조회", description = "과목 정보를 조회합니다")
    public ResponseEntity<CourseVo> getCourseInfo(@PathVariable Long courseId) {
    	CourseVo result = enrollmentService.getCourseInfo(courseId);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/course/{title}")
    @Operation(summary = "이름으로 과목 조회", description = "과목 이름으로 목록을 조회합니다")
    public ResponseEntity<List<CourseVo>> getCoursesByTitle(String title) {
        List<CourseVo> result = enrollmentService.getCoursesByTitle(title);
        return ResponseEntity.ok(result);
    }
    
    
    @PostMapping("/student/enroll")
    @Operation(summary = "학생 등록", description = "신규 학생을 등록합니다")
    public ResponseEntity<String> insertStudent(@RequestBody StudentVo studentVo) {
        enrollmentService.insertStudent(studentVo);
        return ResponseEntity.ok("학생 등록 완료");
    }
    
    @PostMapping("/course/enroll")
    @Operation(summary = "강좌 등록", description = "신규 강좌을 등록합니다")
    public ResponseEntity<String> insertCourse(@RequestBody CourseVo courseVo) {
        enrollmentService.insertCourse(courseVo);
        return ResponseEntity.ok("학생 등록 완료");
    }
    
    @PostMapping("/professor/enroll")
    @Operation(summary = "교수 등록", description = "교수을 등록합니다")
    public ResponseEntity<String> insertProfessor(@RequestBody ProfessorVo  professorVo) {
        enrollmentService.insertProfessor(professorVo);
        return ResponseEntity.ok("교수 등록 완료");
    }
    
    
    @PostMapping("/enroll")
    @Operation(summary = "수강 신청", description = "학생이 과목을 수강 신청합니다")
    public ResponseEntity<String> insertEnroll(@RequestBody EnrollVo enrollVo) {
        enrollmentService.insertEnroll(enrollVo);
        return ResponseEntity.ok("수강 신청 완료");
    }
    
    @PutMapping("/course/enroll/{courseId}")
    @Operation(summary = "과목정보변경", description = "과목정보를 변경합니다")
    public ResponseEntity<String> updateCourseTitle(
    		@Parameter(description = "과목 ID", example = "1") 
    		@PathVariable Long courseId,
            @RequestBody CourseVo courseVo) {
		enrollmentService.updateCourseTitle(courseId, courseVo);
		return ResponseEntity.ok("과목명 수정 완료");
	}
    
    @DeleteMapping("/{courseId}")
    @Operation(summary = "과목삭제", description = "과목을 삭제합니다")
    public String deleteCourse(@Parameter(description = "과목 ID", example = "1")  @PathVariable Long courseId) {
    	enrollmentService.deleteCourse(courseId);
        return "Course deleted: ID = " + courseId;
    }
    
    @PostMapping("/course/studentCount")
    @Operation(summary = "과목당 학생수 조회", description = "과목당 학생수를 조회합니다")
    public ResponseEntity<List<CourseVo>> getCourseWithStudentCount(@RequestBody CourseVo courseVO) {
        List<CourseVo> result = enrollmentService.getCourseWithStudentCount(courseVO);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/course/studentCountWithSubQuery")
    @Operation(summary = "과목당 학생수 조회(subQuery사용)", description = "과목당 학생수를 조회합니다(subQuery사용)")
    public ResponseEntity<List<CourseVo>> getCourseWithStudentCountWithSubQuery() {
        List<CourseVo> result = enrollmentService.getCourseWithStudentCountWithSubQuery();
        return ResponseEntity.ok(result);
    }
    
    
    @GetMapping("/createEntity/{tableName}")
    @Operation(summary = "해당 테이블의 Entity template 자동생성", description = "해당 테이블의 Entity template를 자동생성한다.")
    public ResponseEntity<String> createEntityTemplate(@PathVariable String tableName)  {              
        
        try {
			enrollmentService.createEntityTemplate(tableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body("❌ 오류: " + e.getMessage());
		}        
        return ResponseEntity.ok("✅ Entity template 생성 완료: " + tableName);
        
    }    
}