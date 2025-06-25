# 📘 Student Entity 설명서

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

- `Student`는 수강 내역(`Enrollment`)을 통해 여러 강좌(`Course`)에 연결됩니다
- 실질적으로는 다대다 관계(`Student` ↔ `Course`)를 `Enrollment`가 중간에서 풀어주는 구조

---

## 📂 파일 정보

- 파일 경로: `com.hmg.as.test.hmg_test.entity.Student`
- 이 파일은 학생 정보를 나타내며, 여러 수강 기록을 보유할 수 있도록 설계됩니다

---

## ✅ 기본 필드 정보 

| 필드명 | 타입   | 설명               |
|--------|--------|--------------------|
| id     | Long   | 학생 ID (PK)       |
| name   | String | 학생 이름          |
| major  | String | 전공 또는 학과명    |

```java
@Id
@GeneratedValue
private Long id;

private String name;
private String major;
```

---

## ✅ 관계 매핑 설명

### 🔗 1. `@OneToMany(mappedBy = "student")`

- 관계: `Student 1 ↔ N Enrollment`
- 설명: 한 명의 학생이 여러 과목을 수강할 수 있음
- 반대쪽(Enrollment)에서 `student` 필드를 통해 외래키 관리

```java
@OneToMany(mappedBy = "student")
private List<Enrollment> enrollments = new ArrayList<>();
```

> `mappedBy`를 통해 연관관계의 주인이 아님을 명시 → DB에 외래키 생성되지 않음

---

## ✅ 상속 구조

이 클래스는 `BaseEntity`를 상속받습니다. (예: 생성일, 수정일 등 공통 필드 포함 )

```java
public class Student extends BaseEntity
```

---

## ✅ Lombok 어노테이션 설명

| 어노테이션       | 설명                          |
|------------------|-------------------------------|
| `@Getter/@Setter` | getter, setter 자동 생성       |
| `@Builder`        | 빌더 패턴 메서드 생성          |
| `@NoArgsConstructor` | 기본 생성자 생성           |
| `@AllArgsConstructor` | 전체 필드 생성자 생성     |

---

## ✅ 개발자 요약 가이드

- `Student`는 수강 내역(`Enrollment`)을 통해 과목(`Course`)과 연결됨
- 실질적으로 다대다 관계를 중간 테이블(`Enrollment`)로 풀어낸 구조
- `mappedBy`를 통해 외래키를 직접 관리하지 않고 관계만 표현
- 성능 최적화를 위해 필요한 경우 `FetchType.LAZY` 설정 
