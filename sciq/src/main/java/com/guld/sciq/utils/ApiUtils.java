package com.guld.sciq.utils;


public class ApiUtils {

    public static <T> ApiSuccess<T> success(T response) {
        return new ApiSuccess<>(response);
    }

    public static ApiFail fail(int errorCode, String message) {
        return new ApiFail(errorCode, message);
    }

    public record ApiSuccess<T>(T data) {
    }

    public record ApiFail(int errorCode, String message) {
    }

}
