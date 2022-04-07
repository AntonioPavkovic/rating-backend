package com.internship.ratingbackend.service;

import com.internship.ratingbackend.dto.setting.SettingRequest;
import com.internship.ratingbackend.dto.setting.SettingResponse;
import com.internship.ratingbackend.model.Emotion;
import com.internship.ratingbackend.model.EmotionSetting;
import com.internship.ratingbackend.model.Setting;
import com.internship.ratingbackend.repository.EmotionSettingRepository;
import com.internship.ratingbackend.repository.SettingRepository;
import com.pusher.rest.Pusher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Service class for Setting
 *
 * @see Setting
 */

@AllArgsConstructor
@Service
@Slf4j
public class SettingService {

    private final EmotionSettingRepository emotionSettingRepository;
    private final SettingRepository settingRepository;
    private final Pusher pusher;

    /**
     * Method that requests id to find setting
     *
     * @param id
     * @return setting
     */

    public Setting getSettingById(Integer id)
    {
        return settingRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Setting with id "+id+" doesn't exist"));
    }


    /**
     * Method that requests Setting request and updates the setting
     * @see SettingRequest
     * @param newSetting
     */

    public void updateSetting(SettingRequest newSetting) {

        Optional<Setting> setting = settingRepository.findById(1);
        SettingResponse response = new SettingResponse();
        List<Emotion> emotionList = new ArrayList<>();

        if (setting.isPresent()) {
            setting.get().setEmotionNumber(newSetting.getEmotionNumber());
            setting.get().setMessage(newSetting.getMessage());
            setting.get().setMessageTimeout(newSetting.getMessageTimeout());
            settingRepository.save(setting.get());
            if (setting.get().getMessage() == null) {
                setting.get().setMessage("");
            }
            List<EmotionSetting> listEmotion = emotionSettingRepository.getEmotionSettingByEmotionValue(setting.get().getEmotionNumber());
            for (EmotionSetting emotionSetting : listEmotion) {
                emotionList.add(emotionSetting.getEmotion());
            }
            response.setEmotionNumber(setting.get().getEmotionNumber());
            response.setMessage(setting.get().getMessage());
            response.setMessageTimeout(setting.get().getMessageTimeout());
            response.setEmotions(emotionList);
        }

        log.info("Sending pusher message!");
        pusher.trigger("rating-app", "setting-update", response);

    }
}