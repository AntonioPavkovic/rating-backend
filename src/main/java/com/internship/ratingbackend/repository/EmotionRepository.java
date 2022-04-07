package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Emotion
 *
 * @see Emotion
 */

public interface EmotionRepository extends JpaRepository<Emotion,Integer> {
}
