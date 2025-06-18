package com.hmg.as.test.hmg_test.vo;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class ProfessorVo {
    private String name;
    private String department;
    
//    @QueryProjection
//    public ProfessorVo(String name, String department) {
//        this.name = name;
//        this.department = department;
//    }
}