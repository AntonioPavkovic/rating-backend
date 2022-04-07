package com.internship.ratingbackend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * AuthResponse DTO - used as response with a HttpStatus code and an appropriate message
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    /**
     * Http status code
     */

    private HttpStatus status;

    /**
     * appropriate message
     */

    private String message;
}
