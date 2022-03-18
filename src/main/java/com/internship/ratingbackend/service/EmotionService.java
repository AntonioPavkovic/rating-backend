package com.internship.ratingbackend.service;

import com.internship.ratingbackend.model.Emotion;
import com.internship.ratingbackend.repository.EmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmotionService {
    private final EmotionRepository emotionRepository;

    public Emotion findEmotionById(Integer id)
    {
        return emotionRepository.findById(id).orElseThrow(()->new IllegalStateException("No emotion with id "+id));
    }
}
