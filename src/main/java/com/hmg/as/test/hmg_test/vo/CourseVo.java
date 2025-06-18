package com.hmg.as.test.hmg_test.vo;



import com.querydsl.core.annotations.QueryProjection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class CourseVo {

    @Schema(description = "강좌 Id", example = "1")
    private Long id;

    @Schema(description = "강좌 이름", example = "자료구조")
    private String title;

    @Schema(description = "교수 Id", example = "1")
    private Long professorId;

    @QueryProjection
    public CourseVo(Long id, String title, Long professorId) {
        this.id = id;
        this.title = title;
        this.professorId=professorId;
    }
}
