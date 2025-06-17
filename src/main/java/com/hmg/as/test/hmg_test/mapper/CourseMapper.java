package com.hmg.as.test.hmg_test.mapper;

import java.util.ArrayList;

import com.hmg.as.test.hmg_test.entity.Course;
import com.hmg.as.test.hmg_test.vo.CourseVo;

public class CourseMapper {
	
	public static Course toEntity(CourseVo vo) {
        return Course.builder()
                .title(vo.getTitle())
                .enrollments(new ArrayList<>()) // 초기화 필요
                .build();
    }
}
