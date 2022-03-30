package com.internship.ratingbackend.service;

import java.io.IOException;

import com.slack.api.methods.SlackApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

@Service
public class SlackService {

//    private static String webHooksUrl = "https://hooks.slack.com/services/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";
//    private static String oAuthToken  = "xoxb-3309307669906-3315169949522-L6acVmaoS8nVfcgkmmaUDgR4";
//    private static String slackChannel = "rating-app";
//
//    public static void publishMessage(String id, String text) {
//
//        var client = Slack.getInstance().methods();
//        var logger = LoggerFactory.getLogger(slackChannel);
//
//        try {
//            var result = client.chatPostMessage(r -> r.token(oAuthToken).text(text));
//            logger.info("Result: {} " +result);
//        }catch (IOException | com.github.seratch.jslack.api.methods.SlackApiException e) {
//            logger.error("Error: {}" + e.getMessage(), e);
//        }
//
//    }


    private static final Logger LOGGER = LoggerFactory.getLogger(SlackService.class);
    private static final String NEW_LINE = "\n";
    private static String slackChannel = "#rating-app";

    @Value("${slack.webhook}")
    private String urlSlackWebhook;

    public void sendMessageToSlack(String message) {

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("My message: " + message + NEW_LINE);
        messageBuilder.append("Item example: " + exampleMessage() +NEW_LINE);

        process(messageBuilder.toString());
    }

    private void process(String message) {
        Payload payload = Payload.builder()
                .channel(slackChannel)
                .username("rating")
                .iconEmoji(":rocket:")
                .text(message)
                .build();

        try {
            WebhookResponse webhookResponse = Slack.getInstance().send(urlSlackWebhook, payload);
            LOGGER.info("code -> " + webhookResponse.getCode());
            LOGGER.info("body -> " + webhookResponse.getBody());
        } catch (IOException e) {
            LOGGER.error("Unexpected error! WebHook " + urlSlackWebhook);
        }
    }

    private String exampleMessage() {
        return "Pls work";
    }

}
