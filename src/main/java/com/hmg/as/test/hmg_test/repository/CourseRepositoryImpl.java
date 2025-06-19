package com.hmg.as.test.hmg_test.repository;

import java.util.List;

import com.hmg.as.test.hmg_test.entity.QCourse;
import com.hmg.as.test.hmg_test.entity.QEnrollment;
import com.hmg.as.test.hmg_test.entity.QProfessor;
import com.hmg.as.test.hmg_test.entity.QStudent;
import com.hmg.as.test.hmg_test.vo.CourseVo;
import com.querydsl.core.BooleanBuilder;
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
    public List<CourseVo> getCourseWithStudentCount(CourseVo courseVo) {
        QCourse course = new QCourse("course");        
        QEnrollment enrollment = new QEnrollment("enrollment");
        QProfessor professor = new QProfessor("professor");    
        QStudent student = new QStudent("student");
        
        QEnrollment subEnroll = new QEnrollment("subEnroll");
        QStudent subStudent = new QStudent("subStudent");
        
        
        BooleanBuilder where = new BooleanBuilder();
        
        if (courseVo.getTitle() != null && !courseVo.getTitle().isEmpty()) {
        	where.and(course.title.containsIgnoreCase(courseVo.getTitle())); // where course.title like '%자료구조%'의 의미
        }
        
        if (courseVo.getProfessorName() != null && !courseVo.getProfessorName().isEmpty()) {
        	where.and(course.professor.name.containsIgnoreCase(courseVo.getProfessorName())); // where course.professorName like '%자료구조%'의 의미
        }
        
        //where에 subquery하고 싶을때는 subquery작성후, where.and(작성한 subquery)붙이면 간단히 해결됨 
        QEnrollment maxEnroll = new QEnrollment("maxEnroll");
        QEnrollment minEnroll = new QEnrollment("minEnroll");

        JPQLQuery<String> highestStudentName = JPAExpressions
            .select(student.name)
            .from(maxEnroll)
            .join(maxEnroll.student, student)
            .where(maxEnroll.course.eq(course)
                   .and(maxEnroll.score.eq(
                       JPAExpressions
                           .select(maxEnroll.score.max())
                           .from(maxEnroll)
                           .where(maxEnroll.course.eq(course))
                   ))
            );
        
        JPQLQuery<String> lowestStudentName = JPAExpressions
        	    .select(student.name)
        	    .from(maxEnroll)
        	    .join(maxEnroll.student, student)
        	    .where(
        	    		maxEnroll.course.eq(course)
        	        .and(maxEnroll.score.eq(
        	            JPAExpressions
        	                .select(maxEnroll.score.min())
        	                .from(maxEnroll)
        	                .where(maxEnroll.course.eq(course))
        	        ))
        	    );
        
        return queryFactory
    		    .select(Projections.constructor(
    		            CourseVo.class,
    		            course.id.as("id"),
    		            course.title.as("title"),
    		            professor.id.as("professorId"),
    		            enrollment.countDistinct().as("studentCount"),
    		            enrollment.score.avg().as("averageScore"),
    		            professor.name.as("professorName"),
    		            highestStudentName, //여기에 StringTemplate 사용
    		            lowestStudentName   //여기에 StringTemplate 사용
    		        ))
    		    .from(course)
    		    .leftJoin(course.professor, professor)
    		    .leftJoin(course.enrollments, enrollment)
    		    .where(where)
    		    .groupBy(course.id, course.title, professor.id, professor.name)
    		    .orderBy(course.id.asc())
    		    .fetch();		
        
      
    }

	@Override
	public List<CourseVo> getCourseWithStudentCountWithSubQuery() {
		// TODO Auto-generated method stub
		QCourse course = QCourse.course;
        QEnrollment enrollment = QEnrollment.enrollment;
        QProfessor professor = QProfessor.professor;

//		JPQLQuery<Long> subQuery = JPAExpressions
//			    .select(enrollment.count().as("studentCount"))
//			    .from(enrollment)
//			    .where(enrollment.course.eq(course));

		return queryFactory
		    .select(Projections.fields(
		            CourseVo.class,
		            course.id.as("id"),
		            course.title.as("title"),
		            professor.id.as("professorId"),
		            enrollment.countDistinct().as("studentCount"),
		            professor.name.as("professorName")
		        ))
		    .from(course)
		    .leftJoin(course.professor, professor)
		    .leftJoin(course.enrollments, enrollment)
		    .groupBy(course.id, course.title,professor.id, professor.name)
		    .orderBy(course.id.asc())
		    .fetch();			
		
	}

}
