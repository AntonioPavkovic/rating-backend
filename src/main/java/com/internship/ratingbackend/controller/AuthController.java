package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.dto.auth.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {



    @GetMapping
    public ResponseEntity<TokenResponse> authorize(HttpServletRequest request)
    {
        TokenResponse tokenResponse = new TokenResponse(request.getQueryString());
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
