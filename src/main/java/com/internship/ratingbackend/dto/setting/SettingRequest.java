package com.internship.ratingbackend.dto.setting;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class SettingRequest {

    @Min(value = 3, message = "Minimum value for emotions number is 3")
    @Max(value = 5, message = "Maximum value for emotions number is 5")
    @NotNull
    private Integer emotionNumber;

    @Size(min = 3,max = 128,message = "Message length value must be in bounds 3-128 chars")
    private String message;

    @Min(value = 0, message = "Minimum value for message timeout is 0")
    @Max(value = 10, message = "Maximum value for message timeout is 10")
    @NotNull
    private Integer messageTimeout;


}
