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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final CustomUserRepository customUserRepository;
    private final AppProperties appProperties;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Optional<CustomUser> user = customUserRepository.findCustomUsersByEmail(email);

        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        }
        throw new UsernameNotFoundException("Could not find user");
    }

    public GoogleIdToken.Payload validateToken(TokenRequest token) throws GeneralSecurityException, IOException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),new GsonFactory())
                .setAudience(Collections.singletonList(appProperties.getClientId()))
                .build();

        GoogleIdToken idToken = verifier.verify(token.getToken());
        if (idToken != null) {
            return idToken.getPayload();
        }
        throw new GeneralSecurityException("Invalid token...");
    }

    public void revokeToken(TokenRequest token) throws IOException {
        URL url = new URL(appProperties.getGoogleRevokeTokenLink() + token.getToken());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Length", "0");
        con.setRequestProperty("Accept", "*/*");
        con.setDoOutput(true);
        con.connect();

        con.getOutputStream().close();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        in.close();
        con.disconnect();
    }




}
