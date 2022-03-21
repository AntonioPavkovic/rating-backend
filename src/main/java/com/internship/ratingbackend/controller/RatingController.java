package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.command.CreateRatingCommand;
import com.internship.ratingbackend.model.Rating;
import com.internship.ratingbackend.service.EmotionService;
import com.internship.ratingbackend.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final EmotionService emotionService;

    @GetMapping("/all")
    public List<Rating> getRating() {
        return ratingService.getRating();
    }

    // method/route that gets ratings between two dates
    @GetMapping("")
    public List<Rating> getRatingByCreatedAtBetween(@RequestParam(name = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                    @RequestParam(name = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {

        return ratingService.getRatingByCreatedAtBetween(fromDate, toDate);

    }

    @PostMapping()
    public ResponseEntity<Rating> createRating(@RequestBody @Valid CreateRatingCommand ratingCommand) {
        Rating rating = new Rating(emotionService.findEmotionById(ratingCommand.getEmotionId()));
        ratingService.createRating(rating);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

}
