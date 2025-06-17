package com.hmg.as.test.hmg_test.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;  
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;  

    
    @PrePersist
    protected void onCreate() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
//    // 기본 생성자
//    public User() {
//        this.createdAt = LocalDateTime.now();
//    }
//    
//
//    // 생성자
//    public User(String username, String email) {
//        this.username = username;
//        this.email = email;
//        this.createdAt = LocalDateTime.now();
//    }
   
}