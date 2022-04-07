package com.internship.ratingbackend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * TokenRequest DTO - used for requesting a token for auth
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TokenRequest {

    /**
     * Google Access Token
     */

    @NotNull
    private String token;
}
