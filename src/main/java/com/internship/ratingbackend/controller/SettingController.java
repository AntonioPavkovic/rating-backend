package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.dto.setting.SettingRequest;
import com.internship.ratingbackend.dto.setting.SettingResponse;
import com.internship.ratingbackend.service.EmotionSettingService;
import com.internship.ratingbackend.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * SettingController - a rest controller with a custom settings route for
 * getting and updating settings
 *
 * @see EmotionSettingService
 * @see SettingService
 * @see SettingRequest
 */

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "api/v1/settings")
public class SettingController {

    private final EmotionSettingService emotionSettingService;
    private final SettingService settingService;

    /**
     * API endpoint - GET method for current settings
     *
     * @return emotionSettingService
     */

    @GetMapping()
    public SettingResponse getSetting() {
        return emotionSettingService.getSettingAndEmotionList();
    }

    /**
     * API endpoint - PATCH method for updating settings
     *
     * @param settingRequest
     */

    @PatchMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSetting(@Valid @RequestBody SettingRequest settingRequest) {
        settingService.updateSetting(settingRequest);
    }
}
