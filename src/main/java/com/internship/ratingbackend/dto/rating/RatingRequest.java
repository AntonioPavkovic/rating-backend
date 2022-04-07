package com.internship.ratingbackend.dto.rating;

import lombok.Getter;

/**
 * RatingRequest DTO - used for sending emotionId as a request
 */

@Getter
public class RatingRequest {

    /**
     * Emotion ID
     */

    private Integer emotionId;
}
