# 📘 PostgresEntityGeneratorController

PostgreSQL 데이터베이스에 존재하는 테이블명을 입력받아, 해당 테이블 구조를 기반으로 JPA Entity 클래스를 자동 생성하는 API 컨트롤러입니다  
Swagger 문서에 포함되며, 관리자/개발자 도구 용도로 활용됩니다

---

## 📌 기본 정보

| 항목 | 설명 |
|------|------|
| 컨트롤러명 | `PostgresEntityGeneratorController` |
| 경로 | `/generateEntity/{tableName}` |
| 메서드 | `GET` |
| 설명 | 지정된 테이블명을 기반으로 Entity Java 파일을 자동 생성 |

---

## 🔧 의존 서비스

- `PostgresEntityGeneratorService`  
  테이블 메타 정보를 가져와 Java 코드로 변환하고, 실제 파일로 저장하는 기능을 담당

---

## 🧩 메서드 설명

### `createEntityTemplate(@PathVariable String tableName)`

| 항목 | 설명 |
|------|------|
| HTTP 메서드 | GET |
| URL 패턴 | `/generateEntity/{tableName}` |
| 요청 파라미터 | `tableName` – 생성 대상 테이블 이름 |
| 응답 타입 | `ResponseEntity<String>` |

해당 메서드는 전달받은 테이블명을 기반으로 JPA Entity 클래스를 생성합니다  
`src/main/java/com/hmg/as/test/hmg_test/entity/` 경로에 `.java` 파일이 생성됩니다  

