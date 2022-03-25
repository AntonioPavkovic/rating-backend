package com.internship.ratingbackend.service;

import com.internship.ratingbackend.dto.setting.SettingResponse;
import com.internship.ratingbackend.model.Emotion;
import com.internship.ratingbackend.model.EmotionSetting;
import com.internship.ratingbackend.model.Setting;
import com.internship.ratingbackend.repository.EmotionSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmotionSettingService {

    private final EmotionSettingRepository emotionSettingRepository;
    private final SettingService settingService;

    public SettingResponse getSettingAndEmotionList() {
        Setting setting = settingService.getSettingById(1);
        List<EmotionSetting> emotionSettingsByEmotionValue = this.getEmotionSettingByEmotionValue(setting.getEmotionNumber());
        List<Emotion> emotionList = new ArrayList<>();

        for (EmotionSetting emotionSetting : emotionSettingsByEmotionValue) {
            emotionList.add(emotionSetting.getEmotion());
        }

        return new SettingResponse(setting.getEmotionNumber(), setting.getMessage(), setting.getMessageTimeout(), emotionList);
    }

    public List<EmotionSetting> getEmotionSettingByEmotionValue(Integer emotionValue) {
        return this.emotionSettingRepository.getEmotionSettingByEmotionValue(emotionValue);
    }


}
