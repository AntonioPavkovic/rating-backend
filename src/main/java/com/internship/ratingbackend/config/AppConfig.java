package com.internship.ratingbackend.config;

import com.pusher.rest.Pusher;
import com.github.seratch.jslack.Slack; 
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final AppProperties appProperties;



    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Pusher pusherClient() {
        Pusher pusher = new Pusher(appProperties.getPusherAppId(), appProperties.getPusherKey(), appProperties.getPusherSecret());
        pusher.setCluster(appProperties.getPusherCluster());
        pusher.setEncrypted(true);

        return pusher;
    }

    @Bean
    public Slack getSlack() {
        return Slack.getInstance();
    }
}
