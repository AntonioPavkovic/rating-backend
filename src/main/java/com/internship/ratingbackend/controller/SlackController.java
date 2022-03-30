package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/apps")
public class SlackController {

    private final SlackService slackService;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendSimpleMessageToSlack(@PathVariable("message") String message) {
        slackService.sendMessageToSlack(message);
        return ResponseEntity.ok(message);
    }

}
