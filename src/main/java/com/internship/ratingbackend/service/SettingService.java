package com.internship.ratingbackend.service;

import com.internship.ratingbackend.dto.setting.SettingResponse;
import com.internship.ratingbackend.model.Emotion;
import com.internship.ratingbackend.model.EmotionSetting;
import com.internship.ratingbackend.model.Setting;
import com.internship.ratingbackend.repository.EmotionRepository;
import com.internship.ratingbackend.repository.EmotionSettingRepository;
import com.internship.ratingbackend.repository.SettingRepository;
import com.pusher.rest.Pusher;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
@Slf4j
public class SettingService {
    private final SettingRepository settingRepository;
    private final EmotionSettingRepository emotionSettingRepository;
    private final Pusher pusher;

    public Setting getSettingById(Integer id)
    {
        return settingRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Setting with id "+id+" doesn't exist"));
    }


    public void updateSetting(Setting newSetting) {

            Optional<Setting> setting = settingRepository.findById(1);

            if (setting.isPresent()) {
                setting.get().setEmotionNumber(newSetting.getEmotionNumber());
                setting.get().setMessage(newSetting.getMessage());
                setting.get().setMessageTimeout(newSetting.getMessageTimeout());
                settingRepository.save(setting.get());
                if (setting.get().getMessage() == null) {
                    setting.get().setMessage("");
                }
            }
            List<EmotionSetting> listEmotion = emotionSettingRepository.getEmotionSettingByEmotionValue(setting.get().getEmotionNumber());
            List<Emotion> emotionList = new ArrayList<>();

            for (EmotionSetting emotionSetting : listEmotion) {
                emotionList.add(emotionSetting.getEmotion());
            }

            SettingResponse settingResponse = new SettingResponse(setting.get().getEmotionNumber(), setting.get().getMessage(),
                    setting.get().getMessageTimeout(),emotionList );
        log.info("Sending pusher notification");
//        settingRepository.save(setting);
        pusher.trigger("rating-app", "settings-updated", settingResponse);

    }
}
