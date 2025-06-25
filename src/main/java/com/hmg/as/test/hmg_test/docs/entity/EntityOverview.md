# 📘 Entity 문서 통합 개요

## 📚 엔티티 문서 링크

- [📘 Course Entity 설명](Course.md)
- [📘 Enrollment Entity 설명](Enrollment.md)
- [📘 Professor Entity 설명](Professor.md)
- [📘 Student Entity 설명](Student.md)

---

## 📊 EnrollController과 관련된 Entity 관계 구조 요약

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

- `Professor`는 여러 과목을 담당하고,
- `Course`는 여러 수강 기록을 가지며,
- `Enrollment`는 `Student`와 `Course`를 연결합니다

---
