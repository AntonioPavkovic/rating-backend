package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.dto.RatingDto;
import com.internship.ratingbackend.model.Rating;
import com.internship.ratingbackend.model.Setting;
import com.internship.ratingbackend.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public List<Rating> getRating() {
        return ratingService.getRating();
    }

    // method/route that gets ratings between two dates
    @GetMapping("")
    public List<Rating> getRatingByCreatedAtBetween (@RequestParam(name = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                     @RequestParam(name = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {

        return ratingService.getRatingByCreatedAtBetween(fromDate, toDate);

    }

    @PostMapping()
    public ResponseEntity<Rating> createRating(@RequestBody @Valid RatingDto ratingDto) {

        Rating request = modelMapper.map(ratingDto, Rating.class);
        Rating rating = new Rating(request.getEmotion());
        ratingService.save(rating);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

}
