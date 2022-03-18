package com.internship.ratingbackend.validation;

import com.internship.ratingbackend.model.Emotion;
import com.internship.ratingbackend.service.EmotionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class EmotionExistsConstraintValidator implements ConstraintValidator<EmotionExistValidator, Emotion> {

    private final EmotionService emotionService;
    private final Log log = LogFactory.getLog(EmotionExistsConstraintValidator.class);


    @Autowired
    public EmotionExistsConstraintValidator(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @Override
    public void initialize(EmotionExistValidator emotionExistValidator) {

    }

    @Override
    public boolean isValid(Emotion e, ConstraintValidatorContext constraintValidatorContext) {

        try {
            Emotion emotion = emotionService.findEmotionById(e.getId());
            if (Objects.equals(emotion.getId(), e.getId()) && Objects.equals(emotion.getName(), e.getName()) && Objects.equals(emotion.getColor(), e.getColor()))
                return true;

        } catch (Exception exc) {
            log.error(exc);
        }
        return false;
    }
}

