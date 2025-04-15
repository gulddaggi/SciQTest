package com.guld.sciq.exception;

import com.guld.sciq.common.error.ErrorMessage;
import com.guld.sciq.exception.auth.UserAlreadyExistsException;
import com.guld.sciq.global.exception.*;
import com.guld.sciq.utils.ApiUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex){
        String message = ex.getFieldErrors().get(0).getDefaultMessage() + ": " + ex.getFieldErrors().get(0).getField();
        return createErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return createErrorResponse(HttpStatus.BAD_REQUEST, "잘못된 값을 입력했습니다.");
    }

    // Required Query Parameter를 입력하지 않았을 경우
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        String message = String.format("'%s' 를 입력해주세요.", name);
        return createErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    // Query Parameter -> Long 타입으로 변환 시, Long보다 큰 범위의 숫자를 입력했을 때 Error Handling
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleNumberFormatException(MethodArgumentTypeMismatchException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, "요청 타입이 잘못되었습니다.");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
        return createErrorResponse(HttpStatus.UNAUTHORIZED, ErrorMessage.UNAUTHORIZED_EXCEPTION);
    }

    @ExceptionHandler(DebateNotFoundException.class)
    public ResponseEntity<?> handleDebateNotFoundException(DebateNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DebateCommentNotFoundException.class)
    public ResponseEntity<?> handleDebateCommentNotFoundException(DebateCommentNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidDebateStatusException.class)
    public ResponseEntity<?> handleInvalidDebateStatusException(InvalidDebateStatusException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<?> handleFeedbackNotFoundException(FeedbackNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<?> handleQuestionNotFoundException(QuestionNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownServerError(HttpServletRequest request, Exception ex) {
        String uri = request.getRequestURI();
        if (uri.startsWith("/swagger-ui") || uri.startsWith("/v3/api-docs")) {
            log.error("Unhandled Exception in Swagger-related URI", ex);
            return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
        log.error("Unhandled Exception", ex);
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INTERNAL_SERVER_ERROR);
    }

    private static ResponseEntity<ApiUtils.ApiFail> createErrorResponse(HttpStatus httpStatus, String message) {
        ApiUtils.ApiFail apiFail = ApiUtils.fail(httpStatus.value(), message);
        return new ResponseEntity<>(apiFail, httpStatus);
    }
}
