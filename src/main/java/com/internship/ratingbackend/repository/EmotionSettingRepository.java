package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.EmotionSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmotionSettingRepository extends JpaRepository<EmotionSetting, Integer> {

    @Query(value = "SELECT es FROM EmotionSetting es LEFT JOIN FETCH es.emotion WHERE es.emotionValue=:emotionValue")
    List<EmotionSetting> getEmotionSettingByEmotionValue(@Param("emotionValue") Integer emotionValue);
}
