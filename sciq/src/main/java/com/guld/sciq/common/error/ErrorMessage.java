package com.guld.sciq.common.error;

public class ErrorMessage {
    // 공통 에러 메시지
    public static final String NOT_FOUND = "%s을(를) 찾을 수 없습니다.";
    public static final String ALREADY_EXISTS = "이미 존재하는 %s입니다.";
    public static final String UNAUTHORIZED_EXCEPTION = "유효한 인증이 필요합니다.";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 오류가 발생했습니다.";
    public static final String INVALID_TOKEN_EXCEPTION = "유효하지 않은 토큰입니다.";

    // 도메인별 에러 메시지
    
    // User
	public static final String USER_ALREADY_EXIST = "이미 존재하는 사용자입니다.";
    public static final String USER_ALREADY_LOGOUT = "이미 로그아웃된 사용자입니다.";
    public static final String USER_PROFILE_UPDATE_FAILED = "사용자 프로필 업데이트에 실패했습니다.";
    public static final String USER_PASSWORD_INVALID = "비밀번호는 8자 이상, 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.";
    public static final String USER_PASSWORD_MISMATCH = "비밀번호가 일치하지 않습니다.";
    public static final String USER_PASSWORD_CHANGE_FAILED = "비밀번호 변경에 실패했습니다.";
    public static final String USER_EMAIL_INVALID = "이메일 형식이 올바르지 않습니다.";
    public static final String USER_NICKNAME_INVALID = "닉네임은 2자 이상 10자 이하로 설정해야 합니다.";
    

    // Debate
    public static final String DEBATE_ALREADY_CLOSED = "이미 종료된 토론입니다.";
    public static final String DEBATE_NOT_FOUND = String.format(NOT_FOUND, "토론");
    public static final String DEBATE_NOT_STARTED = "아직 시작되지 않은 토론입니다.";
    public static final String DEBATE_COMMENT_NOT_FOUND = String.format(NOT_FOUND, "토론 댓글");
    public static final String DEBATE_COMMENT_NOT_OWNER = "댓글 작성자가 아닙니다.";
    public static final String DEBATE_DURATION_INVALID = "토론 시간은 1분 이상이어야 합니다.";
    public static final String DEBATE_NOT_OWNER = "토론 작성자가 아닙니다.";
    public static final String DEBATE_ALREADY_OPEN = "이미 열린 토론입니다.";
    public static final String DEBATE_TITLE_REQUIRED = "토론 제목은 필수입니다.";
    public static final String DEBATE_CONTENT_REQUIRED = "토론 내용은 필수입니다.";
    public static final String DEBATE_SCIENCE__REQUIRED = "토론 과학 분야는 필수입니다.";
    public static final String DEBATE_DEADLINE_REQUIRED = "토론 마감일은 필수입니다.";
    public static final String DEBATE_DEADLINE_INVALID = "토론 마감일은 현재 시간보다 이후여야 합니다.";
    public static final String DEBATE_NOT_AUTHORIZED = "토론에 대한 권한이 없습니다.";

    // Feedback
    public static final String FEEDBACK_NOT_FOUND = String.format(NOT_FOUND, "피드백");
    public static final String FEEDBACK_ALREADY_EXISTS = String.format(ALREADY_EXISTS, "피드백");
    public static final String FEEDBACK_ALREADY_LIKED = "이미 좋아요를 누른 피드백입니다.";
    public static final String FEEDBACK_NOT_LIKED = "좋아요를 누르지 않은 피드백입니다.";

    public static final String USER_NOT_FOUND = String.format(NOT_FOUND, "사용자");
    public static final String USER_ALREADY_EXISTS = String.format(ALREADY_EXISTS, "사용자");
    public static final String USER_NOT_AUTHORIZED = "권한이 없는 사용자입니다.";
    public static final String FEEDBACK_TYPE_REQUIRED = "피드백 타입은 필수입니다.";
    public static final String FEEDBACK_NOT_OWNER = "피드백 작성자가 아닙니다.";
    
    // Question
    public static final String QUESTION_NOT_FOUND = "질문을 찾을 수 없습니다.";
    public static final String QUESTION_UPDATE_UNAUTHORIZED = "질문을 수정할 권한이 없습니다.";
    public static final String QUESTION_DELETE_UNAUTHORIZED = "질문을 삭제할 권한이 없습니다.";

    // QuestionComment
    public static final String QUESTION_COMMENT_NOT_FOUND = "댓글을 찾을 수 없습니다.";
    public static final String QUESTION_COMMENT_UPDATE_UNAUTHORIZED = "댓글을 수정할 권한이 없습니다.";
    public static final String QUESTION_COMMENT_DELETE_UNAUTHORIZED = "댓글을 삭제할 권한이 없습니다.";
}