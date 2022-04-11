package com.internship.ratingbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Properties class which reads config keys from application.properties
 */

@Component
@Getter
public class AppProperties {

    /**
     * @Get google client ID
     */

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    /**
     * @Get pusher app ID
     */

    @Value("${app.pusher.app-id}")
    private String pusherAppId;

    /**
     * @Get pusher key
     */

    @Value("${app.pusher.key}")
    private String pusherKey;

    /**
     * @Get pusher secret key
     */

    @Value("${app.pusher.secret}")
    private String pusherSecret;

    /**
     * @Get pusher cluster
     */

    @Value("${app.pusher.cluster}")
    private String pusherCluster;

    /**
     * @Get slack web hook
     */

    @Value("${app.slack.report}")
    private String slackReportLink;

    @Value("${app.google.oauth.validate}")
    private String validateAccessTokenLink;

    @Value("${app.google.oauth.revoke}")
    private String revokeGoogleAccessToken;


  

}
