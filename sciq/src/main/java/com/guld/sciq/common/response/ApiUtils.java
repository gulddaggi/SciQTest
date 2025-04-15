package com.guld.sciq.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ApiUtils {
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, data, null);
    }

    public static ApiResult<?> error(String message) {
        return new ApiResult<>(false, null, message);
    }

    @Getter
    @RequiredArgsConstructor
    public static class ApiResult<T> {
        private final boolean success;
        private final T data;
        private final String message;
    }
} 