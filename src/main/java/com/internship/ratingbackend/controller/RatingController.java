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

/**
 * RatingController - a rest controller with a custom ratings route for creating ratings and getting ratings
 * between two dates
 *
 * @see RatingService
 * @see EmotionService
 * @see RatingResponse
 * @see RatingRequest
 *
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final EmotionService emotionService;

    /**
     * API endpoint - GET method that returns a list of ratings between two dates
     *
     * @param fromDate
     * @param toDate
     * @return ResponseEntity with ratingService and HttpStatus code
     */

    @GetMapping()
    public ResponseEntity<List<RatingResponse>> getRatingByCreatedAtBetween
    (@RequestParam(name = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
     @RequestParam(name = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {

        return new ResponseEntity<>(ratingService.buildAll(fromDate, toDate), HttpStatus.OK);

    }

    /**
     * API endpoint - POST method that creates a rating
     * @param ratingRequest
     * @return ResponseEntity with ratingService and HttpStatus code
     */

    @PostMapping()
    public ResponseEntity<RatingResponse> createRating(@RequestBody @Valid RatingRequest ratingRequest) {
        Rating rating = new Rating(emotionService.findEmotionById(ratingRequest.getEmotionId()));
        ratingService.createRating(rating);
        return new ResponseEntity<>(ratingService.buildSingle(rating), HttpStatus.CREATED);
    }

}
