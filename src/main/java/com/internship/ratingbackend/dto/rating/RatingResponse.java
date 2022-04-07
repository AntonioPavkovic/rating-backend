package com.internship.ratingbackend.dto.rating;

import com.internship.ratingbackend.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * RatingResponse DTO - used as response. Responds with ratings
 *
 * @see Rating
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RatingResponse {

    /**
     * ratings
     */

    private Rating rating;

}
