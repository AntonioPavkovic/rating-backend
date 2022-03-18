package com.internship.ratingbackend.dto;

import com.internship.ratingbackend.model.Emotion;
import com.internship.ratingbackend.validation.EmotionExistValidator;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RatingDto {

    @EmotionExistValidator
    private Emotion emotion;

}
