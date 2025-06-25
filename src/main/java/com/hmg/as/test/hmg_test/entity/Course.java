package com.hmg.as.test.hmg_test.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
@Builder
@AllArgsConstructor 
@NoArgsConstructor
public class Course extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String title;
    

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments = new ArrayList<>();
    
    @ManyToOne(fetch=FetchType.LAZY) //지연로딩 
    @JoinColumn(name = "professor_id")
    private Professor professor;  // 지연로딩 설정시, 연관관계에 있는 professort정보 접근하는 시점에 professor테이블의 조회 쿼리를 실행함 (professor은 proxy 객체로 가지고 있음)
}
