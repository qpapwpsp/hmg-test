# 📘 PostgresEntityGeneratorServiceImple

PostgreSQL 데이터베이스의 테이블 메타 정보를 읽고, 해당 테이블 구조에 기반하여 JPA Entity 클래스를 자동 생성하는 서비스입니다.

---

## 📌 클래스 정보

- **클래스명**: `PostgresEntityGeneratorServiceImple`
- **패키지**: `com.hmg.as.test.hmg_test.service`
- **인터페이스**: `PostgresEntityGeneratorService`
- **Spring Bean 등록**: `@Service`
- **데이터 소스**: Spring Boot의 `application.properties`에 설정된 DataSource 사용

---

## ⚙️ 주요 기능

- 지정된 테이블명으로 PostgreSQL DB 접속
- 테이블 존재 여부 확인
- 기본키 및 컬럼 정보를 기반으로 JPA Entity 생성
- Java 타입으로 자동 매핑
- `/src/main/java/com/hmg/as/test/hmg_test/entity/` 경로에 `.java` 파일 생성

---

## 📂 생성되는 Entity 클래스 구조

- 패키지 선언: `package com.hmg.as.test.hmg_test.entity;`
- 필요한 import 구문 자동 추가
- 클래스명은 테이블명을 PascalCase로 변환
- 필드명은 테이블 컬럼명 그대로 유지
- 기본 어노테이션: `@Entity`, `@Table`, `@Id`, `@Column`

---

## 🧠 동작 방식 요약

1. **경로 확인**
   ```java
   String projectPath = System.getProperty("user.dir");
   
2. **출력 경로 구성**
   String outputPath = projectPath + "/src/main/java/com/hmg/as/test/hmg_test/entity/";
   
3. **데이터베이스 연결 및 메타데이터 조회**
   Connection conn = dataSource.getConnection();
   DatabaseMetaData metaData = conn.getMetaData();
   
4. **테이블 존재 여부 조회**
   ResultSet tables = metaData.getTables(null, null, tableName, new String[]{"TABLE"});
   If (!tables.next()) {
	    throw new IllegalArgumentException("❌ 테이블이 존재하지 않습니다: " + tableName);
   }
 
5. **컬럼 및 기본키 정보 조회**
   ResultSet columns = metaData.getColumns(null, null, tableName, null);
   ResultSet pk = metaData.getPrimaryKeys(null, null, tableName);
   
6. **Java 타입으로 매핑**

7. **Entity 클래스 문자열 구성**
   StringBuilder sb = new StringBuilder();
   sb.append("package ...;\n");
   sb.append("import ...;\n");
   sb.append("@Entity\n@Table(...)\npublic class ClassName { ... }");
 
8. **파일로저장**
  try (FileWriter writer = new FileWriter(outputPath + fileName)) {
    writer.write(sb.toString());
  }
  
   
   