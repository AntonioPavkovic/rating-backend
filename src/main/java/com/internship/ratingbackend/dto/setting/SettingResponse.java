package com.internship.ratingbackend.dto.setting;


import com.internship.ratingbackend.model.Emotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * SettingResponse DTO - responds with an emotionNUmber, message, messageTimeout and a list of emotions
 *
 * @see Emotion
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class SettingResponse {

    /**
     * emotion number
     */
    private Integer emotionNumber;

    /**
     * thank you message
     */

    private String message;

    /**
     * message timeout
     */

    private Integer messageTimeout;

    /**
     * List of emotions
     */

    private List<Emotion> emotions;

}
