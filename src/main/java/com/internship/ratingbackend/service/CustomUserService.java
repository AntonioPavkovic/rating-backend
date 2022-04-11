package com.internship.ratingbackend.service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
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
     * @throws IOException
     */

    public JsonObject validateAccessToken(TokenRequest token) throws IOException {
        URL url = new URL(
                appProperties.getValidateAccessTokenLink() + token.getToken());
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        JsonObject json = JsonParser.parseReader(in).getAsJsonObject();
        in.close();
        con.disconnect();
        return json;
    }

    /**
     *Method revokes passed token, if not valid input, throws IO exception
     *
     * @param token
     * @throws IOException
     */

    public void revokeToken(TokenRequest token) throws IOException {
            URL url = new URL(appProperties.getRevokeGoogleAccessToken() + token.getToken());
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
