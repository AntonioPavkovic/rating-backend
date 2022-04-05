package com.internship.ratingbackend.dto.rating;


import com.internship.ratingbackend.model.Emotion;
import com.internship.ratingbackend.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RatingResponse {

    private Rating rating;

}
