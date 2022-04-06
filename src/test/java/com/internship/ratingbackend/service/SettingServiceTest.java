package com.internship.ratingbackend.service;

import com.internship.ratingbackend.dto.setting.SettingResponse;
import com.internship.ratingbackend.model.Setting;
import com.internship.ratingbackend.repository.EmotionSettingRepository;
import com.internship.ratingbackend.repository.SettingRepository;
import com.pusher.rest.Pusher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SettingServiceTest {

    SettingRepository settingRepository = mock(SettingRepository.class);
    EmotionSettingRepository emotionSettingRepository = mock(EmotionSettingRepository.class);
    Pusher pusher = mock(Pusher.class);

    SettingService settingService = new SettingService(emotionSettingRepository, settingRepository,  pusher);

    @Test
    void getSettingsById() {
        List<Setting> settings = Arrays.asList(
                new Setting(1, 3, "Test", 3)
        );

        when(settingRepository.findById(1)).thenReturn(settings.stream().findFirst());
        Setting setting = settingService.getSettingById(1);
        int responseNum = setting.getEmotionNumber();

        assertEquals(3, responseNum);

    }

}