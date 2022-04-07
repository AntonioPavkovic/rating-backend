package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.EmotionSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository for EmotionSettings
 *
 * @see EmotionSetting
 */

public interface EmotionSettingRepository extends JpaRepository<EmotionSetting, Integer> {

    /**
     * Custom EmotionSetting query
     * @param emotionValue
     * @return List of EmotionSettings found by emotionValue
     */

    @Query(value = "SELECT es FROM EmotionSetting es LEFT JOIN FETCH es.emotion WHERE es.emotionValue=:emotionValue")
    List<EmotionSetting> getEmotionSettingByEmotionValue(@Param("emotionValue") Integer emotionValue);
}
