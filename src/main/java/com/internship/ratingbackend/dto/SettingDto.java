package com.internship.ratingbackend.dto;


import com.internship.ratingbackend.model.Emotion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SettingDto {

    private Integer emotionNumber;
    private String message;
    private Integer messageTimeout;
    List<Emotion> emotions;


}
