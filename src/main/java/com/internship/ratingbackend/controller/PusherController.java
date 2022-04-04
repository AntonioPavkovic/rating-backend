package com.internship.ratingbackend.controller;

import com.pusher.rest.Pusher;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class PusherController {

    private final Pusher pusherClient;

    @GetMapping("/test")
    public void test() {
        pusherClient.trigger("my-channel", "my-event", Collections.singletonMap("message", "hello world"));
    }
}
