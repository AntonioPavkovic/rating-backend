package com.internship.ratingbackend.service;

import java.io.IOException;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import com.slack.api.methods.SlackApiException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

@Service
@RequiredArgsConstructor
public class SlackService {

    //@Value("${slack.team.bot.token}")
    private static String botToken;

    //@Value("${slack.signin.secret}")
    private static String signInSecret

    //@Value("${slack.channel}")
    private static String channel;

    @Scheduled(fixedRate = 1000)
    public static void publishMessage() throws Exception {
        var config = new AppConfig();
        config.setSingleTeamBotToken(botToken);
        config.setSigningSecret(signInSecret);
        var app = new App(config); // `new App()` does the same

        app.client().chatPostMessage(r -> r
                .channel(channel)
                .token(botToken)
                .text("text"));
    }

}

