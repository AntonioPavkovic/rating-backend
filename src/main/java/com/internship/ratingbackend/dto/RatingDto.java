package com.internship.ratingbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RatingDto {

    private LocalDateTime createdAt;

    private Integer emotionId;

}
