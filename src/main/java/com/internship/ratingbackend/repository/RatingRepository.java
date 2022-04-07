package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for Rating
 *
 * @see Rating
 */

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    /**
     * Query all ratings between two dates
     *
     * @param fromDate
     * @param toDate
     * @return List of Ratings between fromDate and toDate
     */
    List<Rating> getRatingByCreatedAtBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
