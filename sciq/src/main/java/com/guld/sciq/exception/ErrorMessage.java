package com.guld.sciq.exception;

public class ErrorMessage {
    // Common
    public static final String INTERNAL_SERVER_ERROR = "내부 서버 에러가 발생했습니다.";

    // Api
    public static final String API_CONTENT_NOT_FOUND = "응답을 생성하는데 실패했습니다.";
    public static final String API_REQUEST_TIMEOUT = "API 호출 시간이 초과됐습니다.";
    public static final String API_UNKNOWN_FINISH_REASON = "알 수 없는 이유로 응답을 불러올 수 없습니다.";

    // Auth
    public static final String INVALID_TOKEN_EXCEPTION = "유효하지 않은 토큰입니다.";
    public static final String UNAUTHORIZED_EXCEPTION = "유효한 인증이 필요합니다.";
    public static final String LOGOUT_USER_NOT_FOUND = "로그아웃 된 사용자입니다.";
    public static final String TOKEN_MISMATCH_EXCEPTION = "토큰의 유저 정보가 일치하지 않습니다.";

    // User
    public static final String USER_ALREADY_LOGOUT = "이미 로그아웃 된 사용자입니다.";
    public static final String USER_ALREADY_EXIST = "이미 존재하는 아이디입니다.";
    public static final String USER_NOT_FOUND = "존재하지 않는 사용자입니다.";

}
