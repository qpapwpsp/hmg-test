package com.hmg.as.test.hmg_test.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorVo {
    private String name;
    private String department;
}