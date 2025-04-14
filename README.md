# SciQ (사이큐) - 과학 커뮤니티 플랫폼 백엔드

## 개요

**SciQ**는 자연과학에 관심 있는 중고등학생들이 심화된 과학 주제에 대해 자유롭게 질문하고 토론할 수 있는 커뮤니티 플랫폼입니다.

---

## 핵심 기능

### 회원 관련
- 회원가입 / 로그인 / 로그아웃
- 세션 기반 인증 처리
- 비밀번호 재설정 및 이메일 인증

### 호기심 게시판 (Q Board)
- 질문 등록, 수정, 삭제
- 댓글 작성 및 삭제
- 추천(좋아요) 기능
- 인기 질문 및 최신 질문 필터링

### 토론 대회 / 이벤트
- 관리자 주제 등록
- 찬반 선택 기능
- 댓글 및 공감 기능
- 토론 상태 관리 (진행 중, 종료 등)

### 자문 피드백 시스템
- GPT 피드백 또는 관리자 피드백 제공
- 피드백 댓글 구분 표시
- 피드백 평가 (도움됨/도움되지 않음)

### 포트폴리오 자동 생성
- 사용자 활동 이력 기반 PDF 문서 자동 생성
- 활동 내역 요약 및 통계 포함

### 검색 시스템
- 키워드 기반 유사 질문 자동 추천
- 질문 및 토론 검색 기능
- 검색 결과 정렬 (관련도, 최신순)

### 예외 처리
- 글로벌 예외 처리 핸들러를 통한 일관된 에러 응답 제공
- 사용자 정의 예외 클래스 활용 (예: `UserNotFoundException`, `InvalidTokenException` 등)

---

## 기술 스택

- **Language**: Java 17
- **Framework**: Spring Boot 3.x
- **Database**: MySQL / H2 (개발용)
- **API 명세**: OpenAPI 3.0 (Swagger)
- **인증**: Spring Session (쿠키 기반 세션 인증)
- **빌드 도구**: Gradle 8.x
- **테스트 프레임워크**: JUnit 5
- **로깅**: SLF4J + Logback
- **ORM**: Hibernate (Spring Data JPA)
- **배포 도구**: Railway 또는 Render

---

## 프로젝트 구조
```
sciq-backend/
├── src/
│   └── main/
│       ├── java/com/sciq/
│       │   ├── controller/         # REST API 컨트롤러
│       │   ├── service/            # 비즈니스 로직 처리
│       │   ├── repository/         # 데이터베이스 접근 계층 (JPA 등)
│       │   ├── entity/             # JPA 엔티티 클래스
│       │   ├── dto/                # 데이터 전송 객체 (Request, Response)
│       │   ├── config/             # Spring 설정 (Security, CORS, Swagger 등)
│       │   ├── exception/          # 커스텀 예외, 전역 예외 핸들러
│       │   └── SciqApplication.java# Spring Boot 애플리케이션 실행 파일
│
│       └── resources/
│           ├── application.yml     # 환경 설정 파일
│           └── static/             # 정적 리소스 (html, css, js 등)
│
├── docs/
│   ├── api-spec.yaml               # Swagger/OpenAPI 명세서
│   └── 기획서.md                   # 프로젝트 기획 문서
│
├── README.md                       # 프로젝트 소개 문서
├── build.gradle                    # Gradle 빌드 스크립트
└── settings.gradle                 # Gradle 설정 파일

```

---

## 기타

### Swagger 설정
- `/swagger-ui.html`에서 API 문서 확인 가능
- JWT 인증을 위한 Bearer 토큰 설정 포함

### JWT 인증
- Access Token 및 Refresh Token 발급
- 토큰 검증 및 재발급 로직 구현
- 사용자 권한 기반 API 접근 제어

### 로깅 및 모니터링
- **로깅**: Logback을 사용하여 애플리케이션 로그 관리
- **모니터링**: Spring Actuator를 활용한 애플리케이션 상태 점검
- **에러 추적**: Sentry 또는 Logstash 연동 가능
