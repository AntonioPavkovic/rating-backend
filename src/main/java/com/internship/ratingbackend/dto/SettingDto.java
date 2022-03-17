package com.internship.ratingbackend.dto;


import com.internship.ratingbackend.model.Emotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@AllArgsConstructor
public class SettingDto {
    @Min(value = 3, message = "Minimum value for emotions number is 3")
    @Max(value = 5, message = "Maximum value for emotions number is 5")
    private Integer emotionNumber;

    @Length(min = 3, message = "Minimum characters for message is 3")
    @Length(max = 128, message = "Maximum characters for message is 128")
    private String message;

    @Min(value = 0, message = "Minimum value for message timeout is 0")
    @Max(value = 10, message = "Maximum value for message timeout is 10")
    private Integer messageTimeout;

    List<Emotion> emotions;


}
