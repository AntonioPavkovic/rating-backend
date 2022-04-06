package com.internship.ratingbackend.service;

import com.internship.ratingbackend.dto.setting.SettingRequest;
import com.internship.ratingbackend.model.Setting;
import com.internship.ratingbackend.repository.SettingRepository;
import com.pusher.rest.Pusher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SettingService {

    private final SettingRepository settingRepository;
    private final Pusher pusher;

    public Setting getSettingById(Integer id)
    {
        return settingRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Setting with id "+id+" doesn't exist"));
    }

    public void updateSetting(SettingRequest newSetting) {

        Optional<Setting> setting = settingRepository.findById(1);

        if(setting.isPresent()) {
            setting.get().setEmotionNumber(newSetting.getEmotionNumber());
            setting.get().setMessage(newSetting.getMessage());
            setting.get().setMessageTimeout(newSetting.getMessageTimeout());
            settingRepository.save(setting.get());
            if (setting.get().getMessage() == null) {
                setting.get().setMessage("");
            }
        }

        log.info("Sending pusher notification");
        pusher.trigger("rating-app", "settings-updated", setting);
        settingRepository.save(setting);


    }
}