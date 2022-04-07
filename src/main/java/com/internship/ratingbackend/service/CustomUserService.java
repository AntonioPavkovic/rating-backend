package com.internship.ratingbackend.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.internship.ratingbackend.config.AppProperties;
import com.internship.ratingbackend.dto.auth.TokenRequest;
import com.internship.ratingbackend.model.CustomUser;
import com.internship.ratingbackend.model.CustomUserDetails;
import com.internship.ratingbackend.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

/**
 * Service class for CustomUser that implements UserDetailService
 *
 * @see CustomUser
 */

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final CustomUserRepository customUserRepository;
    private final AppProperties appProperties;

    /**
     * Method which finds user by username, if it can't find by username throws exception
     *
     * @param email
     * @return user details
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Optional<CustomUser> user = customUserRepository.findCustomUsersByEmail(email);

        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        }
        throw new UsernameNotFoundException("Could not find user");
    }

    /**
     * Method that receives a token and then calls Google to validate it, if unsuccessful throws exception
     *
     * @param token
     * @return token payload
     * @throws GeneralSecurityException
     * @throws IOException
     */

    public GoogleIdToken.Payload validateToken(TokenRequest token) throws GeneralSecurityException, IOException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(appProperties.getClientId()))
                .build();

        GoogleIdToken idToken = verifier.verify(token.getToken());
        if (idToken != null) {
            return idToken.getPayload();
        }
        throw new GeneralSecurityException("Invalid token...");
    }

}
