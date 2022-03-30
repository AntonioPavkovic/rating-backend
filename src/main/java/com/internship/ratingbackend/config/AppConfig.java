package com.internship.ratingbackend.config;

import com.internship.ratingbackend.service.SlackService;
import com.pusher.rest.Pusher;
import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Pusher pusherClient() {
        Pusher pusher = new Pusher("1368402", "6eaf9b744f4f90711be8", "cde0cab392b91ee60215");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);

        return pusher;
    }

//    @Bean
//    public void slackMessage() throws Exception {
//        SlackService slackService = new SlackService();
//        slackService.publishMessage("do u work?");
//    }

}
