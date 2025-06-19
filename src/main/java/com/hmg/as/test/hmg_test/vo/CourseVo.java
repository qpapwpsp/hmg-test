package com.hmg.as.test.hmg_test.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseVo {

    @Schema(description = "강좌 Id", example = "1")
    private Long id;

    @Schema(description = "강좌 이름", example = "자료구조")
    private String title;

    @Schema(description = "교수 Id", example = "1")
    private Long professorId;

    @Schema(description = "해당강좌 수강중인 학생수", example = "10")
    private Long studentCount;

    @Schema(description = "해당강좌 교수이름", example = "김교수")
    private String professorName;

    @Schema(description = "평균점수", example = "87.5")
    private Double averageScore;

    @Schema(description = "최고점수 획득학생명", example = "김최고")
    private String highestScoreStudentName;

    @Schema(description = "최저점수학생명", example = "이최저")
    private String lowestScoreStudentName;

    // Projections.constructor() 대응용 전체 생성자
    public CourseVo(Long id, String title, Long professorId, Long studentCount, Double averageScore,
                    String professorName, String highestScoreStudentName, String lowestScoreStudentName) {
        this.id = id;
        this.title = title;
        this.professorId = professorId;
        this.studentCount = studentCount;
        this.averageScore = averageScore;
        this.professorName = professorName;
        this.highestScoreStudentName = highestScoreStudentName;
        this.lowestScoreStudentName = lowestScoreStudentName;
    }
}