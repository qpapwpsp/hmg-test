package com.hmg.as.test.hmg_test.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollVo {
	@Schema(description = "사번", example = "200100")
    private Long studentId;
	
	@Schema(description = "강좌번호", example = "2357")
    private Long courseId;

	
}
