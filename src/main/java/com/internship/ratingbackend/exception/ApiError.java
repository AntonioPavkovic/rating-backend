package com.internship.ratingbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiError {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status,Throwable throwable) {
        this();
        this.status = status;
        this.message="Unexpected error occurred";
        this.debugMessage=throwable.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, String message,Throwable throwable) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage=throwable.getLocalizedMessage();
    }
}
