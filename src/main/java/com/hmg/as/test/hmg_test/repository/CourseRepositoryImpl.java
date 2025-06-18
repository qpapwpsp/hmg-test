package com.hmg.as.test.hmg_test.repository;

import java.util.List;

import com.hmg.as.test.hmg_test.entity.QCourse;
import com.hmg.as.test.hmg_test.entity.QEnrollment;
import com.hmg.as.test.hmg_test.vo.CourseVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CourseRepositoryImpl  implements CourseRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

    public CourseRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }	

    @Override
    public List<CourseVo> getCourseWithStudentCount() {
        QCourse course = QCourse.course;
        QEnrollment enrollment = QEnrollment.enrollment;

        return queryFactory
                .select(Projections.constructor(
                		CourseVo.class,
                        course.id,
                        course.title,
                        course.professor.id,
                        enrollment.count().as("studentCount")
                ))
                .from(course)
                .leftJoin(course.enrollments, enrollment)
                .groupBy(course.id, course.title)
                .orderBy(course.id.asc())
                .fetch();
    }

	@Override
	public List<CourseVo> getCourseWithStudentCountWithSubQuery() {
		// TODO Auto-generated method stub
		QCourse course = QCourse.course;
        QEnrollment enrollment = QEnrollment.enrollment;

		JPQLQuery<Long> subQuery = JPAExpressions
			    .select(enrollment.count().as("studentCount"))
			    .from(enrollment)
			    .where(enrollment.course.eq(course));

		return queryFactory
		    .select(Projections.constructor(
		    		CourseVo.class,
		        course.id,
		        course.title,
		        course.professor.id,
		        subQuery
		    ))
		    .from(course)
		    .orderBy(course.id.asc())
		    .fetch();
			
	}

}
