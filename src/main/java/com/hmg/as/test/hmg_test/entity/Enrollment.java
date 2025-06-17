package com.hmg.as.test.hmg_test.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
public class Enrollment {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne 
    @JoinColumn(name = "courseId")
    private Course course;
    
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}