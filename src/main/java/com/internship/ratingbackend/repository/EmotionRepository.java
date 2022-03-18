package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion,Integer> {
}
