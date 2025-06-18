package com.hmg.as.test.hmg_test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;
 
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private Long employeeId;

    @Column(name="first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name="last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name="email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name="phone_number",length = 20)
    private String phoneNumber;

    @Column(name="hire_date",nullable = false)
    private LocalDate hireDate;

    @Column(name="job_title", nullable = false, length = 100)
    private String jobTitle;

    @Column(name="is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")  // FK 컬럼
    private Department department;
}