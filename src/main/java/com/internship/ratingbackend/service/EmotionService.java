package com.internship.ratingbackend.service;

import com.internship.ratingbackend.model.Emotion;
import com.internship.ratingbackend.repository.EmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

/**
 * Service class for Emotion
 *
 * @see Emotion
 */

@RequiredArgsConstructor
@Service
public class EmotionService {
    private final EmotionRepository emotionRepository;

    /**
     * Method that finds emotion by id
     *
     * @see EmotionRepository
     * @param id
     * @return emotion
     */

    public Emotion findEmotionById(Integer id)
    {
        return emotionRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No emotion with id "+id));
    }
}
