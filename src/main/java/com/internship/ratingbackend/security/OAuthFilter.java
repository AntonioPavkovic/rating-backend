package com.internship.ratingbackend.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.internship.ratingbackend.dto.auth.TokenRequest;
import com.internship.ratingbackend.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RequiredArgsConstructor
@Component
@Configuration
@Slf4j
public class OAuthFilter extends OncePerRequestFilter {

    private final CustomUserService customUserService;
    private final String BEARER="Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, NullPointerException {

        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        TokenRequest tokenRequest = new TokenRequest();

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            tokenRequest.setToken(authorizationHeader.substring(7));
            try {
                GoogleIdToken.Payload json = customUserService.validateToken(tokenRequest);
                email = json.getEmail();
            } catch (RuntimeException | IOException | GeneralSecurityException e) {
                response.setStatus(401);
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = customUserService.loadUserByUsername(email);
                log.info("User successfully found");
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);

            } catch (UsernameNotFoundException e) {
                log.info("Unable to find a user...");
            }

        }
        filterChain.doFilter(request, response);
    }

    private String extractAuthorizationHeaderAsString(HttpServletRequest request) {
        try {
            return request.getHeader("Authorization");
        } catch (Exception ex){
            throw new RuntimeException("There is no Authorization header in a request", ex);
        }
    }
}
