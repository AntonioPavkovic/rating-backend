package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> getRatingByCreatedAtBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
