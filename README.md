# [Server] Demo Auth Server

![Java](https://img.shields.io/badge/Java-1.8-red.svg) 
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.3.1-green.svg) 
![Hibernate](https://img.shields.io/badge/Hibernate-5.4.6-orange.svg)
![Gradle](https://img.shields.io/badge/Gradle-5.6.2-lightgrey.svg) 
![Swagger](https://img.shields.io/badge/Swagger-2.9.2-blue.svg)
![Lombok](https://img.shields.io/badge/Lombok-1.18.12-blueviolet.svg)
![Guava](https://img.shields.io/badge/Guava-29.0-blueviolet.svg)
![Pojomatic](https://img.shields.io/badge/pojomatic-2.2.1-blueviolet.svg)
![JsonToken](https://img.shields.io/badge/JsonToken-0.9.1-blueviolet.svg)
![QueryDsl](https://img.shields.io/badge/QueryDsl-4.3.1-blueviolet.svg)
![MySql](https://img.shields.io/badge/MySql-8.0.20-blueviolet.svg)

# 알림!
master브랜치를 기본 템플릿으로 기타 변경 작업은 타 브랜치(dev)에서 작업합니다.

># 작업순서
작성중...

# 기술스택
>2020/07/02 기준
- Java         : 1.8
- Spring Boot  : [2.3.1](https://spring.io/projects/spring-boot)
- Hibernate    : [5.4.6]()
- Gradle       : [6.5.1](https://gradle.org/releases/)
- Swagger      : 2.9.2
- Lombok       : [1.18.12](https://projectlombok.org/setup/gradle)
- Guava       : [29.0-jre](https://github.com/google/guava)
- Pojomatic      : 2.2.1
- JsonToken      : 0.9.1
- QueryDsl      : 4.3.1
- MySql      : 8.0.20

># 디렉토리 구조
작성중...

# 커밋메세지
```
커밋 분류코드: 상세내용

- NEW: 신규 파일 및 기능 추가
- IMP: 기능 개선
- SCH: 설정 변경
- CLN: 코드 정리 및 리팩토링
- BUG: 버그 픽스
- TST: 테스트
- DOC: 문서작업
- RMV: 삭제
```

# 참고
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Gradle](https://docs.gradle.org/current/userguide/userguide.html)

---

# 개발표준 정의

## 1. 개요
프로젝트의 개발 시 개발생산성 향상 및 운영의 효율화를 위해 반드시 준수되어야 하는 준수사항을 정의함

 - 제시된 표준은 철저히 준수한다.
 - 모든 화면은 최대 응답속도 5초 이내를 목표로 Design 되어야 한다.
 - 본 표준안은 최소한의 필수사항을 지키도록 유도하고 해서는 안되는 것들을 지적하는 내용이며, 개개인의 다양한 능력과 개념들로 본 표준을 확장시켜 시스템에 적용시키도록 한다.

## 2. 명명규칙 표준

작성중

## 3. Resource 명명규칙

작성중...

## 4. 주석처리
 - 각 Method 앞에 Method의 Parameter, Return Value의 의미와 Method의 간략한 설명을 기술한다.
 - 해당 프로그램의 핵심적인 중요 로직에 대해서는 반드시 그에 따른 주석을 추가한다.
 - 사용하지 않는 로직이라면 지우고 사용예정이라 주석처리를 했다면 반드시 TODO로 작성한다.
 - JavaDoc을 이용하여 프로그램 사양서를 작성할 수 있도록 한다.
 - JavaDoc의 표준을 준수한다.

예 ) 
```
    /**
     * 거래 시작 시 핸들러이다.
     *
     * @param request 요청 데이터
     * @param response 응답 데이터
     * @param handler 핸들러
     *
     * @return 항상 참
     * @throws Exception 예외
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("======================================          START         ======================================");
        log.info(String.format("%s %s", request.getRequestURI(), request.getMethod()));
        return super.preHandle(request, response, handler);
    }
```