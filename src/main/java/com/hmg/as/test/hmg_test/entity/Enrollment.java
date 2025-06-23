package com.hmg.as.test.hmg_test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enrollment")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment extends BaseEntity{
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch=FetchType.LAZY) 
    @JoinColumn(name = "course_id")
    private Course course;
    
    private Integer score; 
    
    
}