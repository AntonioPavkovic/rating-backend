package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.dto.auth.TokenRequest;
import com.internship.ratingbackend.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final CustomUserService customUserService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestBody TokenRequest token) {
        try {
            customUserService.revokeToken(token);
        }catch (IOException e)
        {
            e.getLocalizedMessage();
        }
    }
}
