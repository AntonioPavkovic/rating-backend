package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.dto.rating.RatingRequest;
import com.internship.ratingbackend.dto.rating.RatingResponse;
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
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api/v1/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final EmotionService emotionService;


    @GetMapping()
    public ResponseEntity<List<RatingResponse>> getRatingByCreatedAtBetween
    (@RequestParam(name = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
     @RequestParam(name = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {

        return new ResponseEntity<>(ratingService.buildAll(fromDate, toDate), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<RatingResponse> createRating(@RequestBody @Valid RatingRequest ratingRequest) {
        Rating rating = new Rating(emotionService.findEmotionById(ratingRequest.getEmotionId()));
        ratingService.createRating(rating);
        return new ResponseEntity<>(ratingService.buildSingle(rating), HttpStatus.CREATED);
    }

}
