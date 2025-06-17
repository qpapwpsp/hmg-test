package com.hmg.as.test.hmg_test.mapper;

import java.util.ArrayList;

import com.hmg.as.test.hmg_test.entity.Student;
import com.hmg.as.test.hmg_test.vo.StudentVo;

public class StudentMapper {
	public static Student toEntity(StudentVo vo) {
        return Student.builder()
                .name(vo.getName())
                .enrollments(new ArrayList<>()) // 초기화 필요
                .build();
    }
}
