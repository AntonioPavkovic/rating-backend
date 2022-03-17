package com.internship.ratingbackend.service;

import com.internship.ratingbackend.model.Setting;
import com.internship.ratingbackend.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SettingService {
    private final SettingRepository settingRepository;

    public Setting getSettingById(Integer id)
    {
        return settingRepository.findById(id).orElseThrow(()->new RuntimeException("Setting with id "+id+" doesn't exist"));
    }

    public void updateSetting(Setting newSetting) {

        Setting setting = settingRepository.findById(1).orElseThrow(() -> new IllegalStateException("No setting with id " + 1));

        if (newSetting.getEmotionNumber() != null)
            setting.setEmotionNumber(newSetting.getEmotionNumber());
        if (newSetting.getMessage() != null)
            setting.setMessage(newSetting.getMessage());
        if (newSetting.getMessageTimeout() != null)
            setting.setMessageTimeout(newSetting.getMessageTimeout());

        settingRepository.save(setting);

    }
}
