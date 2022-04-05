package com.internship.ratingbackend.dto.setting;


import com.internship.ratingbackend.model.Emotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SettingResponse {

    private Integer emotionNumber;
    private String message;
    private Integer messageTimeout;
    private List<Emotion> emotions;


}
