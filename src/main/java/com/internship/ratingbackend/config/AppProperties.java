package com.internship.ratingbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppProperties {

    @Value("${app.pusher.app-id}")
    private String pusherAppId;

    @Value("${app.pusher.key}")
    private String pusherKey;

    @Value("${app.pusher.secret}")
    private String pusherSecret;

    @Value("${app.pusher.cluster}")
    private String pusherCluster;

    @Value("${app.slack.report}")
    private String slackReportLink;

}
