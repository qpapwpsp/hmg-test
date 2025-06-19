package com.hmg.as.test.hmg_test.repository;

import java.util.List;

import com.hmg.as.test.hmg_test.vo.CourseVo;

public interface CourseRepositoryCustom {
	List<CourseVo> getCourseWithStudentCount(CourseVo courseVo);
	List<CourseVo> getCourseWithStudentCountWithSubQuery();
}
