package com.hmg.as.test.hmg_test.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentVo {
	@Schema(description = "학생이름", example = "홍길동")
    private String name;
}
