# 📚 Entity & Repository 문서 링크 정리

아래는 지금까지 작성된 각 도메인 구성요소(Course, Enrollment, Professor, Student 등)에 대한 설명 및 구조 분석 문서입니다. 각 항목을 클릭하면 해당 설명으로 이동할 수 있습니다.

## 🗂️ 도메인 문서 목록

### 🔸 Entity 설명및 관계 개요
- [📘 EntityOverview ](./entity/EntityOverview.md)

### 🔸 Controller 설명
- [🖥️ EnrollmentController 설명](./controller/EnrollmentController.md)
- [🖥️ PostgresEntityGeneratorController 설명](./controller/PostgresEntityGeneratorController.md)

### 🔸 Service 설명
- [⚙️ EnrollmentService 설명](./service/EnrollmentService.md)
- [⚙️ PostgresEntityGeneratorService 설명](./service/PostgresEntityGeneratorService.md)

### 🔸 Repository 설명
- [📁 Enrollment과 관련된 Repository 설명](./repository/Enrollment.md)

## 🔍 참고 사항
- 모든 Entity에는 `BaseEntity` 상속을 통해 공통 필드가 포함되어 있음 (작성자, 생성일 등)
- 서비스 계층은 트랜잭션 처리를 담당하며, 복잡한 비즈니스 로직 분기를 포함함
- 복잡한 쿼리는 QueryDSL 기반으로 `CourseRepositoryImpl`에 집중적으로 구현됨

> 각 문서는 개발자가 이해하기 쉽도록 예시 코드, 개념 설명, QueryDSL 서브쿼리 작성법 등을 함께 포함하고 있음
