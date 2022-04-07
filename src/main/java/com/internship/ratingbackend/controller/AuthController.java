package com.internship.ratingbackend.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.internship.ratingbackend.dto.auth.AuthResponse;
import com.internship.ratingbackend.dto.auth.TokenRequest;
import com.internship.ratingbackend.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * AuthController - a rest controller with a custom route for user auth
 *
 * @see CustomUserService
 * @see AuthResponse
 * @see TokenRequest
 *
 */

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final CustomUserService customUserService;

    /**
     * API endpoint - a method that authorizes user
     *
     * @param token
     * @return ResponseEntity with authResponse
     */

    @PostMapping()
    public ResponseEntity<AuthResponse> authorize(@RequestBody @Valid TokenRequest token) {

        AuthResponse authResponse = new AuthResponse();
        try {
            GoogleIdToken.Payload payload = customUserService.validateToken(token);
            String email = payload.getEmail();
            customUserService.loadUserByUsername(email);
            authResponse.setStatus(HttpStatus.ACCEPTED);
            authResponse.setMessage("Successfully authorized!");

            return new ResponseEntity<>(authResponse,HttpStatus.ACCEPTED);
        } catch (GeneralSecurityException | IOException | UsernameNotFoundException e) {
            authResponse.setStatus(HttpStatus.UNAUTHORIZED);
            authResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(authResponse,HttpStatus.UNAUTHORIZED);
        }
    }

}
