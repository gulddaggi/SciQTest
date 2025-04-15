package com.guld.sciq.global.exception;

public class ErrorMessage {
    // 토론 관련 에러 메시지
    public static final String DEBATE_NOT_FOUND = "토론을 찾을 수 없습니다.";
    public static final String DEBATE_COMMENT_NOT_FOUND = "댓글을 찾을 수 없습니다.";
    public static final String DEBATE_ALREADY_EXISTS = "이미 존재하는 토론입니다.";
    public static final String DEBATE_TITLE_REQUIRED = "토론 제목은 필수입니다.";
    public static final String DEBATE_CONTENT_REQUIRED = "토론 내용은 필수입니다.";
    public static final String DEBATE_STANCE_REQUIRED = "토론 입장은 필수입니다.";
    public static final String DEBATE_CATEGORY_REQUIRED = "토론 카테고리는 필수입니다.";
    public static final String DEBATE_TOPIC_REQUIRED = "토론 주제는 필수입니다.";
    public static final String DEBATE_DEADLINE_REQUIRED = "토론 마감일은 필수입니다.";
    public static final String DEBATE_DEADLINE_INVALID = "토론 마감일이 유효하지 않습니다.";
    public static final String DEBATE_DEADLINE_PASSED = "토론 마감일이 지났습니다.";
    public static final String DEBATE_ALREADY_CLOSED = "이미 종료된 토론입니다.";
    public static final String DEBATE_NOT_CLOSED = "아직 종료되지 않은 토론입니다.";
    public static final String DEBATE_NOT_OWNER = "토론 작성자만 수정/삭제할 수 있습니다.";
    public static final String DEBATE_COMMENT_NOT_OWNER = "댓글 작성자만 수정/삭제할 수 있습니다.";
    public static final String DEBATE_COMMENT_CONTENT_REQUIRED = "댓글 내용은 필수입니다.";
    public static final String DEBATE_COMMENT_STANCE_REQUIRED = "댓글 입장은 필수입니다.";
    public static final String DEBATE_COMMENT_USER_NICKNAME_REQUIRED = "댓글 작성자 닉네임은 필수입니다.";
    public static final String DEBATE_ALREADY_OPEN = "이미 시작된 토론입니다.";
    public static final String DEBATE_DURATION_INVALID = "토론 시간이 유효하지 않습니다.";

    // 사용자 관련 에러 메시지
    public static final String USER_NOT_FOUND = "사용자를 찾을 수 없습니다.";
    public static final String USER_ALREADY_EXISTS = "이미 존재하는 사용자입니다.";
    public static final String USER_EMAIL_REQUIRED = "이메일은 필수입니다.";
    public static final String USER_PASSWORD_REQUIRED = "비밀번호는 필수입니다.";
    public static final String USER_NICKNAME_REQUIRED = "닉네임은 필수입니다.";
    public static final String USER_EMAIL_INVALID = "유효하지 않은 이메일 형식입니다.";
    public static final String USER_PASSWORD_INVALID = "비밀번호는 최소 8자 이상이어야 하며, 영문, 숫자, 특수문자를 포함해야 합니다.";
    public static final String USER_NICKNAME_INVALID = "닉네임은 2자 이상 20자 이하의 한글, 영문, 숫자만 사용 가능합니다.";
    public static final String USER_PASSWORD_MISMATCH = "비밀번호가 일치하지 않습니다.";
    public static final String USER_NOT_AUTHORIZED = "권한이 없는 사용자입니다.";
    public static final String USER_ACCOUNT_DISABLED = "비활성화된 계정입니다.";
    public static final String USER_ACCOUNT_LOCKED = "잠긴 계정입니다. 관리자에게 문의하세요.";
    public static final String USER_ACCOUNT_EXPIRED = "만료된 계정입니다.";
    public static final String USER_CREDENTIALS_EXPIRED = "비밀번호가 만료되었습니다.";
    public static final String USER_SESSION_EXPIRED = "세션이 만료되었습니다. 다시 로그인해주세요.";
    public static final String USER_PROFILE_UPDATE_FAILED = "프로필 업데이트에 실패했습니다.";
    public static final String USER_PASSWORD_CHANGE_FAILED = "비밀번호 변경에 실패했습니다.";

    // 피드백 관련 에러 메시지
    public static final String FEEDBACK_NOT_FOUND = "피드백을 찾을 수 없습니다.";
    public static final String FEEDBACK_CONTENT_REQUIRED = "피드백 내용은 필수입니다.";
    public static final String FEEDBACK_TYPE_REQUIRED = "피드백 유형은 필수입니다.";
    public static final String FEEDBACK_NOT_OWNER = "피드백 작성자만 수정/삭제할 수 있습니다.";
    public static final String FEEDBACK_TARGET_REQUIRED = "질문 또는 토론 중 하나는 반드시 선택해야 합니다.";
    public static final String QUESTION_NOT_FOUND = "질문을 찾을 수 없습니다.";

    // 일반 에러 메시지
    public static final String UNAUTHORIZED = "인증되지 않은 사용자입니다.";
    public static final String FORBIDDEN = "접근 권한이 없습니다.";
    public static final String BAD_REQUEST = "잘못된 요청입니다.";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 오류가 발생했습니다.";
    public static final String RESOURCE_NOT_FOUND = "요청한 리소스를 찾을 수 없습니다.";
    public static final String METHOD_NOT_ALLOWED = "허용되지 않은 메서드입니다.";
    public static final String VALIDATION_ERROR = "입력값이 유효하지 않습니다.";
    public static final String REQUEST_TIMEOUT = "요청 시간이 초과되었습니다.";
    public static final String SERVICE_UNAVAILABLE = "서비스를 일시적으로 사용할 수 없습니다.";
    public static final String CONFLICT = "리소스 충돌이 발생했습니다.";
    public static final String TOO_MANY_REQUESTS = "요청 횟수가 초과되었습니다. 잠시 후 다시 시도해주세요.";
    public static final String NETWORK_ERROR = "네트워크 오류가 발생했습니다.";
    public static final String DATABASE_ERROR = "데이터베이스 오류가 발생했습니다.";
    public static final String FILE_UPLOAD_ERROR = "파일 업로드에 실패했습니다.";
    public static final String FILE_DOWNLOAD_ERROR = "파일 다운로드에 실패했습니다.";
    public static final String INVALID_FORMAT = "잘못된 형식입니다.";
    public static final String INVALID_PARAMETER = "잘못된 파라미터입니다.";
    public static final String MISSING_PARAMETER = "필수 파라미터가 누락되었습니다.";
    public static final String DUPLICATE_REQUEST = "중복된 요청입니다.";
    public static final String RATE_LIMIT_EXCEEDED = "요청 제한을 초과했습니다.";
} 