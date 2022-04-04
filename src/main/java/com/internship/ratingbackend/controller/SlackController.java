package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/apps")
public class SlackController {

    private final SlackService slackService;


    public void sendMessage() throws Exception {
        slackService.publishMessage();
    }

}
