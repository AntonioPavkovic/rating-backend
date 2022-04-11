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
        URL url = new URL(appProperties.getValidateAccessTokenLink() + token.getToken());
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setRequestMethod("GET");

        BufferedReader input = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        JsonObject json = JsonParser.parseReader(input).getAsJsonObject();
        input.close();
        httpsURLConnection.disconnect();
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
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Length", "0");
            httpURLConnection.setRequestProperty("Accept", "*/*");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            httpURLConnection.getOutputStream().close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));
            in.close();
            httpURLConnection.disconnect();
    }

}
