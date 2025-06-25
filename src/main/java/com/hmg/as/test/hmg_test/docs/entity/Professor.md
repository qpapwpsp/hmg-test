# 📘 Professor Entity 설명서

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

- `Professor`는 여러 `Course`를 담당할 수 있는 상위 엔티티입니다
- 각 `Course`는 특정 `Professor`에 소속되며, 지연 로딩으로 관리됩니다

---

## 📂 파일 정보

- 파일 경로: `com.hmg.as.test.hmg_test.entity.Professor`
- 이 파일은 교수 정보를 관리하며, `Course` 엔티티와 연관 관계를 가집니다

---

## ✅ 기본 필드 정보 

| 필드명     | 타입   | 설명               |
|------------|--------|--------------------|
| id         | Long   | 교수 ID (PK)       |
| name       | String | 교수 이름          |
| department | String | 소속 학과 또는 전공 |

```java
@Id
@GeneratedValue
private Long id;

private String name;
private String department;
```

---

## ✅ 관계 매핑 설명

### 🔗 1. `@OneToMany(mappedBy = "professor")`

- 관계: `Professor 1 ↔ N Course`
- 설명: 한 명의 교수가 여러 강좌를 담당함
- Course에서 `@ManyToOne`으로 연관관계를 설정하고 있으므로, 이쪽은 `mappedBy` 설정

```java
@OneToMany(mappedBy = "professor")
private List<Course> courses = new ArrayList<>();
```

> 🔎 양방향 매핑을 꼭 사용해야 하는 경우가 아니라면 단방향으로도 충분한 경우 많음

---

## ✅ 상속 구조

이 클래스는 `BaseEntity`를 상속받고 있다. (생성일자, 수정일자 등 공통 항목들)

```java
public class Professor extends BaseEntity
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

- `Professor`는 강좌(`Course`) 엔티티와 1:N 관계로 연결됨
- 실무에서는 학과, 직위, 담당 과목 수 등의 도메인 정보를 함께 관리
- `Course` 엔티티에서 `professor_id` 외래키로 관리되며, Lazy 로딩으로 쿼리 최적화 가능
- 연관관계 관리는 주로 `Course` 측에서 수행하며, 필요에 따라 `Professor`에서도 강좌 리스트 조회 가능
