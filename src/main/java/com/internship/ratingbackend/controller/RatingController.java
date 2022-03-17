package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.model.Rating;
import com.internship.ratingbackend.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ratings")
public class RatingController {

    private final RatingService ratingService;

    @GetMapping()
    public List<Rating> getRating() {
        return ratingService.getRating();
    }

    // method/route that gets ratings between two dates
    @GetMapping("/between")
    public List<Rating> getRatingByCreatedAtBetween (@RequestParam(name = "created_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime fromDate,
                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime toDate) {

        return ratingService.getRatingByCreatedAtBetween(fromDate, toDate);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rating> createRating(@RequestBody Rating newRating) {

        Rating rating = ratingService.save(newRating);

        if (rating == null) {
            System.out.println("Error");
        }

        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

}
