# 📘 Enrollment Entity 설명서

## 📊 Entity 관계 구조 (계층도)

```
Professor
   ▲
   │ (N:1)
Course
   ▲
   │ (1:N)
Enrollment
   ▼
Student
```

- `Enrollment`는 학생(Student)이 과목(Course)을 수강한 기록을 나타내는 중간 테이블  
- 각 Enrollment는 하나의 학생과 하나의 과목에 연결되며, 추가 속성(score)을 가짐

---

## 📂 파일 정보

- **파일 경로**: `com.hmg.as.test.hmg_test.entity.Enrollment`  
- 이 파일은 수강신청 내역을 나타내는 중간 엔티티로, 학생과 과목을 연결하고 성적(score) 정보를 포함합니다

---

## ✅ 기본 필드 정보

| 필드명 | 타입     | 설명                     |
|--------|----------|--------------------------|
| id     | Long     | 수강 PK (자동 생성)      |
| score  | Integer  | 수강 후 부여된 점수      |

```java
@Id
@GeneratedValue
private Long id;

private Integer score;
```

---

## ✅ 관계 매핑 설명

### 🔗 1. `@ManyToOne(fetch = FetchType.LAZY)` – Student

- 관계: `Enrollment N ↔ 1 Student`  
- 설명: 하나의 수강 내역은 한 명의 학생에 속함  
- 외래키: `student_id`

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "student_id")
private Student student;
```

---

### 🔗 2. `@ManyToOne(fetch = FetchType.LAZY)` – Course

- 관계: `Enrollment N ↔ 1 Course`  
- 설명: 하나의 수강 내역은 하나의 과목에 속함  
- 외래키: `course_id`

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "course_id")
private Course course;
```

> 🔍 두 관계 모두 `LAZY` 로딩으로 설정하여 실제 필요한 시점에만 연관된 엔티티 조회

---

## ✅ 상속 구조

이 클래스는 `BaseEntity`를 상속받습니다. (생성일, 수정일 등 공통 필드 포함 가능)

```java
public class Enrollment extends BaseEntity
```

---

## ✅ Lombok 어노테이션 설명

| 어노테이션             | 설명                             |
|------------------------|----------------------------------|
| `@Getter/@Setter`      | getter, setter 자동 생성         |
| `@Builder`             | 빌더 패턴 메서드 생성            |
| `@NoArgsConstructor`   | 기본 생성자 생성                 |
| `@AllArgsConstructor`  | 전체 필드 생성자 생성            |

---

## ✅ 개발자 요약 가이드

- `Enrollment`는 수강 테이블로, 학생과 과목의 다대다 관계를 풀어주는 중간 엔티티
- `score` 필드는 단순 다대다 매핑이 아닌 엔티티로 분리한 이유가 됨 (속성 보유)
- 연관관계 설정 시 `mappedBy`가 아닌 외래키 소유 측이며, 필요 시 `cascade` 옵션을 설정할 수 있음
- 성능을 고려해 `FetchType.LAZY` 설정이 권장됨
