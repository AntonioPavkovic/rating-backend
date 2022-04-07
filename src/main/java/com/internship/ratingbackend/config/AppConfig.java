package com.internship.ratingbackend.config;


import com.pusher.rest.Pusher;
import com.slack.api.Slack;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config class for our API
 */

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final AppProperties appProperties;

    /**
     * Method that configures Pusher and returns a singleton pusher bean
     *
     * @return pusher
     */

    @Bean
    public Pusher pusherClient() {
        Pusher pusher = new Pusher(appProperties.getPusherAppId(), appProperties.getPusherKey(), appProperties.getPusherSecret());
        pusher.setCluster(appProperties.getPusherCluster());
        pusher.setEncrypted(true);

        return pusher;
    }

    /**
     * Method that returns an instance of slack
     *
     * @return Slack.getInstance
     */

    @Bean
    public Slack getSlack() {
        return Slack.getInstance();
    }

}
