package com.guld.sciq.global.exception;

public record ErrorResponse(
    int status,
    String message
) {
} 