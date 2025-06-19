package com.hmg.as.test.hmg_test.service;

import java.util.List;

import com.hmg.as.test.hmg_test.vo.CourseVo;
import com.hmg.as.test.hmg_test.vo.EnrollVo;
import com.hmg.as.test.hmg_test.vo.ProfessorVo;
import com.hmg.as.test.hmg_test.vo.StudentVo;

public interface EnrollmentService {
	List<EnrollVo> getCoursesByStudentId(Long studentId);
	Long insertStudent(StudentVo studentVo);
	Long insertCourse(CourseVo courseVo);
	Long insertProfessor(ProfessorVo professorVo);
	void insertEnroll(EnrollVo enrollVo);
	List<CourseVo> getCoursesByTitle(String title);
	void updateCourseTitle(Long courseId, CourseVo courseVo);
	void deleteCourse(Long courseId);
	void deleteStudent(Long studentId);
	List<CourseVo> getCourseWithStudentCount(CourseVo courseVO);
	List<CourseVo> getCourseWithStudentCountWithSubQuery();
}
