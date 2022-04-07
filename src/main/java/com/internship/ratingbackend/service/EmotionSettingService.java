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

/**
 * Service class for EmotionSetting
 *
 * @see EmotionSetting
 */

@RequiredArgsConstructor
@Service
public class EmotionSettingService {

    private final EmotionSettingRepository emotionSettingRepository;
    private final SettingService settingService;

    /**
     * Method that gets setting and a list of emotions
     *
     * @see Setting
     * @see SettingResponse
     *
     * @return new SettingResponse
     */

    public SettingResponse getSettingAndEmotionList() {
        Setting setting = settingService.getSettingById(1);
        List<EmotionSetting> emotionSettingsByEmotionValue = this.getEmotionSettingByEmotionValue(setting.getEmotionNumber());
        List<Emotion> emotionList = new ArrayList<>();

        for (EmotionSetting emotionSetting : emotionSettingsByEmotionValue) {
            emotionList.add(emotionSetting.getEmotion());
        }

        return new SettingResponse(setting.getEmotionNumber(), setting.getMessage(), setting.getMessageTimeout(), emotionList);
    }

    /**
     * Method that gets emotion_settings by emotionValue param
     *
     * @see  EmotionSettingRepository
     * @param emotionValue
     * @return emotion_setting
     */

    public List<EmotionSetting> getEmotionSettingByEmotionValue(Integer emotionValue) {
        return this.emotionSettingRepository.getEmotionSettingByEmotionValue(emotionValue);
    }


}
