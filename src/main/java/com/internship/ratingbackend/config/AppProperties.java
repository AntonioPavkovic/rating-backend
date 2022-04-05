package com.internship.ratingbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppProperties {
    @Value("${app.google.oauth.revoke}")
    private String googleRevokeTokenLink;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

}
