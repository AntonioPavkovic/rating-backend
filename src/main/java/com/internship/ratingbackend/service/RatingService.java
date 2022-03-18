package com.internship.ratingbackend.service;

import com.internship.ratingbackend.model.Rating;
import com.internship.ratingbackend.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    public List<Rating> getRating() {
        return ratingRepository.findAll();
    }

    public List<Rating> getRatingByCreatedAtBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        return ratingRepository.getRatingByCreatedAtBetween(fromDate, toDate);
    }

    public Rating save(Rating newRating) {
        return ratingRepository.save(newRating);
    }
}
