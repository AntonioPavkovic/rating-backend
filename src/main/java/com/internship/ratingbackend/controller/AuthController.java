package com.internship.ratingbackend.controller;

import com.google.gson.JsonObject;
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
    public ResponseEntity<AuthResponse> authorize(@RequestBody @Valid TokenRequest token)throws IOException {

        AuthResponse authResponse = new AuthResponse();
        try {
            JsonObject payload = customUserService.validateAccessToken(token);
            String email = payload.get("email").getAsString();
            customUserService.loadUserByUsername(email);
            authResponse.setStatus(HttpStatus.ACCEPTED);
            authResponse.setMessage("Successfully authorized!");

            return new ResponseEntity<>(authResponse,HttpStatus.ACCEPTED);
        } catch (IOException | UsernameNotFoundException e) {
            authResponse.setStatus(HttpStatus.UNAUTHORIZED);
            authResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(authResponse,HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("revoke")
    public ResponseEntity<AuthResponse> revokeToken(@RequestBody TokenRequest token)
    {
    AuthResponse authResponse = new AuthResponse();
        try {
            customUserService.revokeToken(token);
            authResponse.setStatus(HttpStatus.ACCEPTED);
            authResponse.setMessage("Token revoked successfully");

            return new ResponseEntity<>(authResponse,HttpStatus.OK);
        }catch (IOException e)
        {
            authResponse.setStatus(HttpStatus.BAD_REQUEST);
            authResponse.setMessage("Something went wrong...");

            return new ResponseEntity<>(authResponse,HttpStatus.BAD_REQUEST);
        }
    }

}
