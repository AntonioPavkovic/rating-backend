package com.internship.ratingbackend.service;

import com.internship.ratingbackend.model.Setting;
import com.internship.ratingbackend.repository.SettingRepository;
import com.pusher.rest.Pusher;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@AllArgsConstructor
@Service
@Slf4j
public class SettingService {
    private final SettingRepository settingRepository;
    private final EmotionService emotionService;
    private final Pusher pusher;

    public Setting getSettingById(Integer id)
    {
        return settingRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Setting with id "+id+" doesn't exist"));
    }


    public void updateSetting(Setting newSetting) {

        Setting setting = settingRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("No setting with id 1"));

        if (newSetting.getEmotionNumber() != null)
            setting.setEmotionNumber(newSetting.getEmotionNumber());

        setting.setMessage(newSetting.getMessage());

        if (newSetting.getMessageTimeout() != null)
            setting.setMessageTimeout(newSetting.getMessageTimeout());

        log.info("Sending pusher notification");
        pusher.trigger("rating-app", "settings-updated", setting);
        settingRepository.save(setting);

    }
}
