package com.hmg.as.test.hmg_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmg.as.test.hmg_test.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
