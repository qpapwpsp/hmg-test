package com.hmg.as.test.hmg_test.vo;

import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class  StudentVo{
	@Schema(description = "학생이름", example = "홍길동")
    private String name;
	
	@QueryProjection
    public StudentVo(String name) {
        this.name = name;
    }
}
