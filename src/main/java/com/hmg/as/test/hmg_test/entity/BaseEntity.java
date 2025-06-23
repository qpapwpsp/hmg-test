package com.hmg.as.test.hmg_test.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	@CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;	
	
    @Column(updatable = false)
    private String createdUserId;

   

    @LastModifiedDate
    private LocalDateTime updatedAt;    

    private String updatedUserId;
  
}
