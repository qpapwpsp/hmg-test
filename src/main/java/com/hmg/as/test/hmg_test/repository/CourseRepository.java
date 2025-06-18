package com.hmg.as.test.hmg_test.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmg.as.test.hmg_test.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, CourseRepositoryCustom {
    // 필요 시 커스텀 쿼리 추가 가능
	List<Course> findByTitleContaining(String title,  Sort sort); // 강좌제목으로 조회
}
