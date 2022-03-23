package com.internship.ratingbackend.controller;

import com.internship.ratingbackend.dto.setting.SettingRequest;
import com.internship.ratingbackend.dto.setting.SettingResponse;
import com.internship.ratingbackend.model.Setting;
import com.internship.ratingbackend.service.EmotionSettingService;
import com.internship.ratingbackend.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/settings")
public class SettingController {

    private final EmotionSettingService emotionSettingService;
    private final SettingService settingService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public SettingResponse getSetting() {
        return emotionSettingService.getSettingAndEmotionList();
    }

    @PatchMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSetting(@Valid @RequestBody SettingRequest settingRequest) {
        Setting request = modelMapper.map(settingRequest, Setting.class);
        settingService.updateSetting(request);
    }
}
