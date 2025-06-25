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

- `Student`는 여러 수강기록(`Enrollment`)을 보유하며, 간접적으로 여러 과목(`Course`)에 연결됨
- 다대다 관계(`Student` ↔ `Course`)를 `Enrollment` 엔티티로 분리해 관리하는 구조

---

## 📂 파일 정보

- 파일 경로: `com.hmg.as.test.hmg_test.entity.Student`
- 이 파일은 학생 정보를 나타내며, 수강 내역과 연관된 정보를 관리합니다

---

## ✅ 기본 필드 정보

| 필드명 | 타입   | 설명                 |
|--------|--------|----------------------|
| id     | Long   | 학생 ID (PK)         |
| name   | String | 학생 이름            |
| grade  | Long   | 학년 또는 학급 정보  |

```java
@Id
@GeneratedValue
private Long id;

private String name;
private Long grade;
```

---

## ✅ 관계 매핑 설명

### 🔗 1. `@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)`

- 관계: `Student 1 ↔ N Enrollment`
- 설명: 학생은 여러 과목을 수강할 수 있으며, 수강 내역이 삭제되거나 등록될 때 함께 반영됨
- `cascade = ALL`: 학생 저장/삭제 시 연관된 수강 기록도 함께 처리됨
- `orphanRemoval = true`: 수강 내역이 리스트에서 제거되면 DB에서도 삭제됨

```java
@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Enrollment> enrollments = new ArrayList<>();
```

> 💡 `mappedBy`는 연관관계의 주인이 아니라는 뜻 (외래키는 Enrollment가 가짐)

---

## ✅ 상속 구조

이 클래스는 `BaseEntity`를 상속받습니다. (생성일, 수정일 등 공통 필드 포함)

```java
public class Student extends BaseEntity
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

- `Student`는 수강 내역(`Enrollment`)을 통해 간접적으로 과목(`Course`)과 연결됨
- `cascade` 및 `orphanRemoval` 설정을 통해 수강 관리 자동화
- 연관관계는 양방향이며, 학생 삭제 시 연관 수강 내역도 같이 삭제됨