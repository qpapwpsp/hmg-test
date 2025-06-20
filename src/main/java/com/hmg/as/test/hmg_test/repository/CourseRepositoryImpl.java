package com.hmg.as.test.hmg_test.repository;

import java.util.Arrays;
import java.util.List;

import com.hmg.as.test.hmg_test.entity.QCourse;
import com.hmg.as.test.hmg_test.entity.QEnrollment;
import com.hmg.as.test.hmg_test.entity.QProfessor;
import com.hmg.as.test.hmg_test.entity.QStudent;
import com.hmg.as.test.hmg_test.vo.CourseVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * 수행되는 쿼리
 *  SELECT
	        COURSE.ID,
	        UPPER(COURSE.TITLE),
	        PROFESSOR.ID,
	        COUNT(DISTINCT ENROLLMENT.ID),
	        AVG(ENROLLMENT.SCORE),
	        PROFESSOR.NAME,
	        (SELECT S1_0.NAME 
	           FROM ENROLLMENT E2_0 
	           JOIN STUDENTS S1_0 
	             ON S1_0.ID=E2_0.STUDENT_ID 
	          WHERE E2_0.COURSE_ID=COURSE.ID 
	            AND E2_0.SCORE=(
	                SELECT MAX(E3_0.SCORE) 
	                  FROM ENROLLMENT E3_0 
	                 where E3_0.COURSE_ID=COURSE.ID
	               )),
	        (SELECT STUDENT.NAME 
	           FROM ENROLLMENT E4_0 
	           JOIN STUDENTS STUDENT 
	             ON STUDENT.ID=E4_0.STUDENT_ID 
	          WHERE E4_0.COURSE_ID=COURSE.ID 
	            AND E4_0.SCORE=(
	                SELECT MIN(E5_0.SCORE) 
	                  FROM ENROLLMENT E5_0 
	                 WHERE E5_0.COURSE_ID=COURSE.ID
	               )),
	        CASE WHEN (COUNT(DISTINCT ENROLLMENT.ID)>?) THEN CAST(? AS TEXT) 
	             WHEN (COUNT(DISTINCT ENROLLMENT.ID)=?) THEN CAST(? AS TEXT) 
	             ELSE '비인기강좌' 
	        END 
	  FROM COURSE COURSE 
	  LEFT JOIN PROFESSOR PROFESSOR 
	    ON PROFESSOR.ID=COURSE.PROFESSOR_ID 
	  LEFT JOIN ENROLLMENT ENROLLMENT 
	    ON COURSE.ID=ENROLLMENT.COURSE_ID 
	 WHERE PROFESSOR.ID IN (?, ?) 
	 GROUP BY
		   COURSE.ID,
		   COURSE.TITLE,
		   PROFESSOR.ID,
		   PROFESSOR.NAME 
	 ORDER BY
	       COURSE.ID
 * 
 */
public class CourseRepositoryImpl  implements CourseRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

    public CourseRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
    
    @Override
    public List<CourseVo> getCourseWithStudentCount(CourseVo courseVo) {
        QCourse course = QCourse.course;//new QCourse("course");        
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
        
       // WHERE PROFESSOR.ID IN (1,2)        
        List<Long> professorIds = Arrays.asList(1L, 2L);
        where.and(course.professor.id.in(professorIds)); 
        
        //where에 subquery하고 싶을때는 subquery작성후, where.and(작성한 subquery)붙이면 간단히 해결됨 
        QEnrollment maxEnroll = new QEnrollment("maxEnroll");
        QEnrollment minEnroll = new QEnrollment("minEnroll");
        
        /*SUB QUERY*/
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
        
        
//        // 최고 점수 학생 이름
//        StringTemplate highestStudentName = stringTemplate(
//            "(select s.name from enrollment e join students s on s.id = e.student_id where e.course_id = {0} and e.score = (select max(e2.score) from enrollment e2 where e2.course_id = {0}) limit 1)",
//            course.id
//        );
//
//        // 최저 점수 학생 이름
//        StringTemplate lowestStudentName = stringTemplate(
//            "(select s.name from enrollment e join students s on s.id = e.student_id where e.course_id = {0} and e.score = (select min(e2.score) from enrollment e2 where e2.course_id = {0}) limit 1)",
//            course.id
//        );
        

        
        return queryFactory
    		    .select(Projections.constructor(
    		            CourseVo.class,
    		            course.id.as("id"),
    		            course.title.toUpperCase().as("title"),
    		            professor.id.as("professorId"),
    		            enrollment.countDistinct().as("studentCount"),
    		            enrollment.score.avg().as("averageScore"),
    		            professor.name.as("professorName"),
    		            highestStudentName, //여기에 StringTemplate 사용
    		            lowestStudentName,  //여기에 StringTemplate 사용
    		            new CaseBuilder().when(enrollment.countDistinct().gt(2L))
    		            .then("인기강좌") 
    		            .when(enrollment.countDistinct().eq(0L))
    		            .then("폐강강좌")                                               
                        .otherwise("비인기강좌")
                        .as("인기여부")
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
