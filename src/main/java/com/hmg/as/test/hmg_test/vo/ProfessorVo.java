package com.hmg.as.test.hmg_test.vo;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@Builder
public class ProfessorVo {
    private String name;
    private String department;
    
    @QueryProjection
    public ProfessorVo(String name, String department) {
        this.name = name;
        this.department = department;
    }
}