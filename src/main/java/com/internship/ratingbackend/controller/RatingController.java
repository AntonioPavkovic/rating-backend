package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.dto.rating.RatingRequest;
import com.internship.ratingbackend.dto.rating.RatingResponse;
import com.internship.ratingbackend.model.Rating;
import com.internship.ratingbackend.service.EmotionService;
import com.internship.ratingbackend.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RatingController {

    private final RatingService ratingService;
    private final EmotionService emotionService;


    // method/route that gets ratings between two dates
    @GetMapping()
    public ResponseEntity<List<RatingResponse>> getRatingByCreatedAtBetween(@RequestParam(name = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                                            @RequestParam(name = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {

        return new ResponseEntity<>(ratingService.buildAll(fromDate, toDate), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RatingResponse> createRating(@Valid @RequestBody RatingRequest ratingRequest) {

        RatingResponse response = new RatingResponse();

        try {
            log.info("Creating rating!");
            response = ratingService.createRating(ratingRequest);

            if (response.getRating() != null) {
                log.info("Rating created!");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            else {
                log.warn("Error occurred while creating rating!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            log.error("An error has occurred!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

}
