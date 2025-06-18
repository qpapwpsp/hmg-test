package com.hmg.as.test.hmg_test.mapper;

import java.util.ArrayList;

import com.hmg.as.test.hmg_test.entity.Professor;
import com.hmg.as.test.hmg_test.vo.ProfessorVo;

public class ProfessorMapper {
	public static Professor toEntity(ProfessorVo vo) {
		Professor professor = new Professor();
		professor.setName(vo.getName());
		professor.setDepartment(vo.getDepartment());
		professor.setCourses(new ArrayList<>());
		
		return professor;
//	        return Professor.builder()
//                .name(vo.getName())
//                .department(vo.getDepartment())
//                .courses(new ArrayList<>()) // 초기화 필요
//                .build();
    }
}
