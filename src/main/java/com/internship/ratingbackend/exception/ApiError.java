package com.internship.ratingbackend.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

/**
 * ApiError Exception handler - this class is used to return a custom api_error
 */

@Getter
@Setter
public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Zagreb")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;


    /**
     * custom constructors
     */
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
