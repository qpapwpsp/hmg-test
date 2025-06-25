package com.hmg.as.test.hmg_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmg.as.test.hmg_test.entity.Enrollment;
import com.hmg.as.test.hmg_test.vo.EnrollVo;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // 필요 시 커스텀 쿼리 추가 가능	

	List<EnrollVo> findCoursesByStudentId(Long studentId);
}

