package com.hmg.as.test.hmg_test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hmg.as.test.hmg_test.entity.Course;
import com.hmg.as.test.hmg_test.entity.Enrollment;
import com.hmg.as.test.hmg_test.entity.Professor;
import com.hmg.as.test.hmg_test.entity.Student;
import com.hmg.as.test.hmg_test.mapper.CourseMapper;
import com.hmg.as.test.hmg_test.mapper.ProfessorMapper;
import com.hmg.as.test.hmg_test.mapper.StudentMapper;
import com.hmg.as.test.hmg_test.repository.CourseRepository;
import com.hmg.as.test.hmg_test.repository.EnrollmentRepository;
import com.hmg.as.test.hmg_test.repository.ProfessorRepository;
import com.hmg.as.test.hmg_test.repository.StudentRepository;
import com.hmg.as.test.hmg_test.vo.CourseVo;
import com.hmg.as.test.hmg_test.vo.EnrollVo;
import com.hmg.as.test.hmg_test.vo.ProfessorVo;
import com.hmg.as.test.hmg_test.vo.StudentVo;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.transaction.Transactional;

@Service
public class EnrollmentServiceImple implements EnrollmentService {

	private final EnrollmentRepository enrollmentRepository;
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
//	private final CourseRepositoryImpl courseRepositoryImpl;
	private final ProfessorRepository professorRepository;
	private final JPAQueryFactory queryFactory;

	public EnrollmentServiceImple(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository,
			CourseRepository courseRepository, ProfessorRepository professorRepository, JPAQueryFactory queryFactory) {
		this.enrollmentRepository = enrollmentRepository;
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.professorRepository = professorRepository;
		this.queryFactory = queryFactory;
		// this.courseRepositoryImpl=courseRepositoryImpl;
	}

	@Override
	public List<EnrollVo> getCoursesByStudentId(Long studentId) {
		// TODO Auto-generated method stub
		return enrollmentRepository.findCoursesByStudentId(studentId);
	}

	@Override
	@Transactional
	public void insertEnroll(EnrollVo enrollVo) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(enrollVo.getStudentId())
				.orElseThrow(() -> new RuntimeException("학생 없음"));
		Course course = courseRepository.findById(enrollVo.getCourseId())
				.orElseThrow(() -> new RuntimeException("강좌 없음"));

		Enrollment enrollment = Enrollment.builder().student(student).course(course).build();

		enrollmentRepository.save(enrollment);
	}

	@Override
	public Long insertCourse(CourseVo courseVo) {
		// courseRepository Auto-generated method stub
		Professor professor = professorRepository.findById(courseVo.getProfessorId())
				.orElseThrow(() -> new IllegalArgumentException("해당 교수 없음"));

		Course course = CourseMapper.toEntity(courseVo);
		course.setProfessor(professor);

		return courseRepository.save(course).getId();
	}

	@Override
	public Long insertStudent(StudentVo studentVo) {
		// TODO Auto-generated method stub
		Student student = StudentMapper.toEntity(studentVo);
		return studentRepository.save(student).getId();
	}

	@Override
	public List<CourseVo> getCoursesByTitle(String title) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Sort.Direction.ASC, "title");
		List<Course> courses = courseRepository.findByTitleContaining(title, sort);

		return courses.stream().map(course -> CourseVo.builder().id(course.getId()).title(course.getTitle()).build())
				.toList();
	}

	@Override
	public void updateCourseTitle(Long courseId, CourseVo courseVo) {
		// TODO Auto-generated method stub
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("강좌 없음"));
		course.setTitle(courseVo.getTitle());
		courseRepository.save(course);
	}

	@Override
	public void deleteCourse(Long courseId) {
		// TODO Auto-generated method stub
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new IllegalArgumentException("과목이 존재하지 않습니다"));

		if (!course.getEnrollments().isEmpty()) {
			throw new IllegalStateException("수강생이 등록된 과목은 삭제할 수 없습니다");
		}

		courseRepository.deleteById(courseId);
	}

	@Override
	public void deleteStudent(Long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다"));

		studentRepository.delete(student);
	}

	@Override
	public Long insertProfessor(ProfessorVo professorVo) {
		// TODO Auto-generated method stub
		Professor professor = ProfessorMapper.toEntity(professorVo);
		return professorRepository.save(professor).getId();
	}

	@Override
	public List<CourseVo> getCourseWithStudentCount(CourseVo courseVo) {
		// TODO Auto-generated method stub
		return courseRepository.getCourseWithStudentCount(courseVo);
	}

	@Override
	public List<CourseVo> getCourseWithStudentCountWithSubQuery() {
		// TODO Auto-generated method stub
		return courseRepository.getCourseWithStudentCountWithSubQuery();
	}

	@Override
	public CourseVo getCourseInfo(Long courseId) {
		// TODO Auto-generated method stub
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("강좌를 찾을 수 없습니다"));
		CourseVo courseVo = new CourseVo();
		courseVo.builder()
			.id(course.getId())
			.title(course.getTitle())
			.professorId(course.getProfessor().getId())
			.professorName(course.getProfessor().getName())
			.build();
		
		return courseVo;
	}

}
