package com.internship.ratingbackend.security;


import com.internship.ratingbackend.service.CustomUserDetailsImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthSuccessHandler implements AuthenticationSuccessHandler {


    private final CustomUserDetailsImp customUserDetailsImp;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
        try {
            customUserDetailsImp.loadUserByUsername(oauthUser.getEmail());
            response.sendRedirect("/api/v1/auth?Bearer=" + oauthUser.getIdToken().getTokenValue());
        } catch (UsernameNotFoundException e) {
            response.setStatus(401);
        }
    }
}
