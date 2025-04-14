# SciQ (사이큐) - 과학 커뮤니티 플랫폼 백엔드

## 개요

**SciQ**는 자연과학에 관심 있는 중고등학생들이 심화된 과학 주제에 대해 자유롭게 질문하고 토론할 수 있는 커뮤니티 플랫폼입니다.  
이 프로젝트는 해당 플랫폼의 **백엔드 서버**를 구현한 저장소입니다.

---

## 핵심 기능

### 회원 관련
- 회원가입 / 로그인 / 로그아웃
- 세션 기반 인증 처리

### 호기심 게시판 (Q Board)
- 질문 등록, 수정, 삭제
- 댓글 작성 및 삭제
- 추천(좋아요) 기능

### 토론 대회 / 이벤트
- 관리자 주제 등록
- 찬반 선택 기능
- 댓글 및 공감 기능

### 자문 피드백 시스템
- GPT 피드백 또는 관리자 피드백 제공
- 피드백 댓글 구분 표시

### 포트폴리오 자동 생성
- 사용자 활동 이력 기반 PDF 문서 자동 생성

### 검색 시스템
- 키워드 기반 유사 질문 자동 추천

---

## 기술 스택

- **Language**: Java 17
- **Framework**: Spring Boot 3.x
- **Database**: MySQL / H2 (개발용)
- **API 명세**: OpenAPI 3.0 (Swagger)
- **인증**: Spring Session (쿠키 기반 세션 인증)
- **배포 도구**: Railway 또는 Render

---

## 프로젝트 구조 (예시)

```
sciq-backend/
├── src/
│   ├── main/java/com/sciq/
│   │   ├── controller/
│   │   ├── domain/
│   │   ├── repository/
│   │   ├── service/
│   │   └── SciqApplication.java
│   └── resources/
│       ├── application.yml
│       └── static/
├── docs/
│   ├── api-spec.yaml
│   └── 기획서.md
├── README.md
└── build.gradle
```

---

