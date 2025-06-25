# 📘 Repository 구성 및 설명서

## 📚 개요
본 문서는 프로젝트에서 사용되는 JPA 기반 Repository들의 구성과 역할, 그리고 관련된 커스텀 구현체 `CourseRepositoryImpl`에 대한 설명을 포함합니다. 모든 Repository는 Spring Data JPA 기반으로 작성되어 있으며, 일부는 QueryDSL을 활용하여 복잡한 조회 로직을 구현합니다.

---

## 📂 Repository 목록

### 1. `CourseRepository`
- 경로: `com.hmg.as.test.hmg_test.repository.CourseRepository`
- 역할: `Course` 엔티티에 대한 기본 CRUD 제공
- 상속: `JpaRepository<Course, Long>`, `CourseRepositoryCustom`
- 주요 메서드:
  ```java
  List<Course> findByTitleContaining(String title, Sort sort);
  ```
  - `title` 컬럼을 포함하는 문자열로 검색 (정렬 포함)

### 2. `CourseRepositoryCustom`
- 커스텀 인터페이스로, `CourseVo` 기반의 통계성 조회 결과를 위한 선언부
- 구현체: `CourseRepositoryImpl`

### 3. `EnrollmentRepository`
- 경로: `com.hmg.as.test.hmg_test.repository.EnrollmentRepository`
- 역할: `Enrollment` 엔티티에 대한 CRUD + 커스텀 조회 제공
- 주요 메서드:
  ```java
  List<EnrollVo> findCoursesByStudentId(Long studentId);
  ```
  - 특정 학생이 수강한 강좌 리스트 반환 (DTO 변환)

### 4. `ProfessorRepository`
- 경로: `com.hmg.as.test.hmg_test.repository.ProfessorRepository`
- 역할: `Professor` 엔티티에 대한 기본 CRUD 처리
- 커스텀 기능 없음

### 5. `StudentRepository`
- 경로: `com.hmg.as.test.hmg_test.repository.StudentRepository`
- 역할: `Student` 엔티티에 대한 기본 CRUD 처리
- 커스텀 기능 없음

---

## 🔍 커스텀 구현체: `CourseRepositoryImpl`

### 📁 클래스 정보
- 경로: `com.hmg.as.test.hmg_test.repository.CourseRepositoryImpl`
- 역할: QueryDSL 기반으로 복잡한 통계 쿼리를 수행하여 `CourseVo` 반환

### 📌 주요 쿼리 기능
1. 교수 ID 목록에 속한 강좌만 필터링
2. 학생 수, 평균 점수, 최고/최저 득점자 이름, 인기 강좌 분류 등 다양한 통계 처리
3. 서브쿼리를 사용한 최고 점수/최저 점수 학생 조회
4. `CaseBuilder`를 활용한 조건별 분류

### ✅ 주요 메서드 설명

#### getCourseWithStudentCount(CourseVo courseVo)
- QueryDSL을 사용하여 통계 정보 포함 강좌 리스트 조회
- `WHERE`, `JOIN`, `GROUP BY`, `SUBQUERY`, `CASE` 등 다양한 JPQL 기능 포함
- 반환 필드:
  - `id`, `title`, `professorId`, `studentCount`, `averageScore`, `professorName`, `highestStudentName`, `lowestStudentName`, `인기여부`

#### getCourseWithStudentCountWithSubQuery()
- 단순 통계용 쿼리로, 서브쿼리 없이 그룹핑과 조인을 통한 요약 정보만 조회

---

## 🧠 사용된 JPA/QueryDSL 개념 요약

| 개념 | 설명 |
|------|------|
| `JpaRepository` | CRUD 메서드 자동 생성 인터페이스 |
| `@Repository` | Bean 등록용 어노테이션 (예외 변환 포함) |
| Query Method | 메서드 명만으로 조건 쿼리 작성 가능 (`findByTitleContaining`) |
| QueryDSL | Java 코드로 타입 안전한 JPQL 작성 도구 |
| `Projections.constructor/fields` | DTO 매핑 방법 |
| `BooleanBuilder` | 조건을 동적으로 구성할 수 있는 객체 |
| `JPAExpressions` | 서브쿼리 작성 도구 |
| `CaseBuilder` | 조건 분기 처리 (CASE WHEN THEN) |
| `LEFT JOIN` | 연관 테이블을 조건 없이 조인할 수 있도록 설정 |

---

## ⚙️ Maven 기준 QueryDSL 설정 및 Q클래스 생성 방법

### 1. Maven 설정 추가
```xml
<dependencies>
  <dependency>
    <groupId>com.querydsl</groupId>
    <artifactId>querydsl-jpa</artifactId>
    <version>${querydsl.version}</version>
  </dependency>
  <dependency>
    <groupId>com.querydsl</groupId>
    <artifactId>querydsl-apt</artifactId>
    <version>${querydsl.version}</version>
    <scope>provided</scope>
  </dependency>
</dependencies>

<build>
  <plugins>
    <plugin>
      <groupId>com.mysema.maven</groupId>
      <artifactId>apt-maven-plugin</artifactId>
      <version>1.1.3</version>
      <executions>
        <execution>
          <goals>
            <goal>process</goal>
          </goals>
          <configuration>
            <outputDirectory>target/generated-sources/java</outputDirectory>
            <processor>com.querydsl.apt.jpa.JPAAnnotationProcessor</processor>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

### 2. Q클래스 컴파일 방법
```bash
# 터미널 또는 명령 프롬프트에서 실행
mvn compile
```
- `target/generated-sources/java` 경로에 `QCourse`, `QStudent` 등 자동 생성됨
- 이 경로를 IDE에서 `Generated Sources Root`로 설정하면 자동 완성 지원 가능

### 3. Q클래스 사용 예시
```java
QCourse course = QCourse.course;
QProfessor professor = QProfessor.professor;
QEnrollment enrollment = new QEnrollment("enrollment");
```
- `.course`와 같은 static 필드 사용 가능
- 생성자 파라미터로 별칭 지정 가능 (서브쿼리 또는 여러 조인 시 활용)

---

## ✍️ 정리
- Repository는 엔티티별로 기본적인 JPA 기능을 담당하며, 복잡한 조회는 Custom Repository + QueryDSL로 분리하여 구현함
- 유지보수성 향상과 쿼리의 명확성 확보를 위해 명시적 DTO와 명확한 조건 필터링 구조 사용
- QueryDSL은 실무에서 복잡한 조건, 동적 쿼리, 서브쿼리 등에 매우 유용하며, 타입 안정성과 가독성을 제공함

