# 📘 EnrollmentService 구현 설명서

## 🧩 클래스 개요

`EnrollmentServiceImple`은 학생 수강 등록 시스템의 핵심 비즈니스 로직을 담당하는 서비스 클래스입니다. 이 클래스는 Repository 및 Mapper 객체를 주입받아 수강 등록, 강좌 관리, 교수/학생 등록, 조건 검색 등의 기능을 수행합니다.

- 위치: `com.hmg.as.test.hmg_test.service.EnrollmentServiceImple`
- 인터페이스: `EnrollmentService` 구현체
- 주요 기능:
  - 수강신청 등록 및 조회
  - 강좌 생성/삭제/수정
  - 교수, 학생 등록
  - 과목별 수강생 수 집계 (서브쿼리 포함)

---

## ✅ 주요 메서드 설명

### 📌 getCoursesByStudentId(Long studentId)
- 특정 학생이 수강 중인 과목 리스트를 조회
- 반환: `List<EnrollVo>`
- JPA Repository 메서드 사용

---

### 📌 insertEnroll(EnrollVo enrollVo)
- 수강 신청 등록
- 내부에서 `Student`, `Course`를 조회해 `Enrollment` 생성 후 저장
- `@Transactional` 적용으로 트랜잭션 보장

```java
@Transactional
public void insertEnroll(EnrollVo enrollVo) {
    Student student = studentRepository.findById(...);
    Course course = courseRepository.findById(...);
    Enrollment enrollment = Enrollment.builder()
        .student(student)
        .course(course)
        .build();
    enrollmentRepository.save(enrollment);
}
```

**JPA 개념 설명**:
- `@Transactional`: 수강 신청은 DB에 2개 이상의 엔티티를 저장하는 작업이므로 트랜잭션 보장이 필요
- `orElseThrow`: Optional의 null 안전 처리
- `builder()`: Lombok 빌더를 활용한 객체 생성

---

### 📌 insertCourse(CourseVo courseVo)
- 강좌 생성
- 교수 ID로 교수 엔티티 조회 후, `CourseMapper`로 변환하여 저장

### 📌 getCoursesByTitle(String title)
- 과목명으로 Like 검색 수행 (정렬 포함)
- `Sort.by(...)` 사용하여 제목 오름차순 정렬 적용

### 📌 updateCourseTitle(Long courseId, CourseVo courseVo)
- 과목 ID로 조회 후 과목명 수정
- `save()` 호출 시 Dirty Checking 적용

**JPA 개념 설명**:
- **Dirty Checking**: 영속성 컨텍스트에 의해 감지된 필드 변경은 트랜잭션 종료 시 자동으로 update 처리됨

---

### 📌 deleteCourse(Long courseId)
- 과목 삭제 전 수강 내역 존재 여부 확인
- 수강생이 등록된 강좌는 예외 발생

```java
if (!course.getEnrollments().isEmpty()) {
    throw new IllegalStateException("수강생이 등록된 과목은 삭제할 수 없습니다");
}
```

**JPA 개념 설명**:
- `OneToMany` 연관 관계를 통해 수강생 목록을 조회
- 삭제 제약 조건 비즈니스 로직으로 명시적 처리

---

### 📌 getCourseWithStudentCount(), getCourseWithStudentCountWithSubQuery()
- 각각 일반 방식과 서브쿼리 방식으로 학생 수 통계 조회
- 내부에서 `CourseRepository`의 커스텀 메서드 활용 (예: `@Query`, QueryDSL)

---

### 📌 insertStudent, insertProfessor
- Mapper를 통해 VO → Entity 변환 후 저장
- 각각 `StudentMapper.toEntity()`, `ProfessorMapper.toEntity()` 사용

---

## 🧠 사용된 JPA 개념 정리

| 개념 | 설명 |
|------|------|
| `@Transactional` | 메서드 내 작업이 하나라도 실패 시 전체 롤백 처리 |
| `Optional.orElseThrow()` | NullPointerException 방지, 명시적 예외 처리 |
| `Lazy Loading` | 연관된 엔티티는 실제 접근할 때까지 SQL 실행 지연 |
| `CascadeType.ALL` | 연관된 엔티티도 함께 저장/삭제 가능 |
| `Dirty Checking` | 변경 감지 기능으로 엔티티 수정 시 자동 update 처리 |
| `Repository.save()` | 신규 저장(INSERT) 또는 병합(MERGE) 처리 |

---

