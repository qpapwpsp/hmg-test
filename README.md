# AS 영역 IT 프로젝트 및 DevOps 추진
Spring Boot, JPA, react.js 기반 모바일 애플리케이션

## 소개
본 프로젝트는 AS 영역 IT 프로젝트 및 DevOps 추진을 위한 선행 단계로서,  
Spring Boot와 JPA를 중심으로 한 기술 스택에 대한 이해와 활용 능력을 향상시키기 위한 테스트 프로젝트입니다.

## 기술 스택
- Java 17
- Spring Boot 3.4.6
- JPA (Hibernate) 
- react.js 
- QueryDSL 5.0.0
- PostgreSQL
- Mybatis Framework 3.0.4

## 프로젝트 구조
src/main/java/com/hmg/as/test/hmg_test/  
├── config/             // 환경 설정 관련 (JPA 설정, DB 설정 등)  
├── controller/         // REST API 엔드포인트 정의  
├── entity/             // JPA 엔티티 (데이터 모델)  
├── mapper/             // MyBatis Mapper 인터페이스 (및 MybatisFramework 3.0.4)  
├── repository/         // JPA Repository 인터페이스  
├── service/            // 비즈니스 로직 처리 (서비스 인터페이스)  
├── service/impl/       // 서비스 인터페이스 구현체  
└── vo/                 // Value Object (또는 DTO 역할)  
  
src/main/resources/  
├── mapper/                // MyBatis XML Mapper 파일  
├── META-INF/  
│   └── persistence.xml    // JPA 관련 설정  
└── application.properties // 애플리케이션 설정 파일  

### gitHub 로컬에 내려받기
```java
git clone https://github.com/qpapwpsp/hmg-test.git
```

### Swagger 링크
[Swagger 링크](http://localhost:8080/swagger-ui/index.html)

### 샘플
1. JPA  
    1. EmployeeServiceImpl.java  
2. QueryDSL  
    1. EnrollmentServiceImple.java  
3. criteria  
    1. RoInfoServiceImpl.java  
