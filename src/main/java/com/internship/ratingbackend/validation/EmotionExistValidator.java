package com.internship.ratingbackend.validation;

import javax.validation.Constraint;
import java.lang.annotation.*;



@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy= EmotionExistsConstraintValidator.class)
public @interface EmotionExistValidator {
    String message() default "Emotion doesn't exists";
    Class[] groups() default {};
    Class[] payload() default {};
}
