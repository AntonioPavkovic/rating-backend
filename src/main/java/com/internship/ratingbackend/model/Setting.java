package com.internship.ratingbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "setting")
public class Setting {
    private static final List<Integer> ALLOWED_EMOTION_NUMBER_VALUES = List.of(3, 4, 5);
    private static final Integer DEFAULT_EMOTION_NUMBER_VALUES = 3;

    private static final Integer MIN_MESSAGE_LENGTH_VALUE = 3;
    private static final Integer MAX_MESSAGE_LENGTH_VALUE = 128;
    private static final String DEFAULT_MESSAGE_VALUE = "Thank you for rating";

    private static final Integer MIN_MESSAGE_TIMEOUT_VALUE = 0;
    private static final Integer MAX_MESSAGE_TIMEOUT_VALUE = 10;
    private static final Integer DEFAULT_MESSAGE_TIMEOUT_VALUE = 5;


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id")
    private Integer id;


    @Column(name = "emotion_number", nullable = false, columnDefinition = "SMALLINT")
    private Integer emotionNumber;


    @Column(name = "message", nullable = false, columnDefinition = "VARCHAR(128)")
    private String message;

    @Column(name = "message_timeout", nullable = false, columnDefinition = "SMALLINT")
    private Integer messageTimeout;


    public Setting(Integer emotionNumber, String message, Integer messageTimeout) {

        this.emotionNumber = Objects.requireNonNullElse(emotionNumber, DEFAULT_EMOTION_NUMBER_VALUES);
        this.message = Objects.requireNonNullElse(message, DEFAULT_MESSAGE_VALUE);
        this.messageTimeout = Objects.requireNonNullElse(messageTimeout, DEFAULT_MESSAGE_TIMEOUT_VALUE);

        try {
            if ((!ALLOWED_EMOTION_NUMBER_VALUES.contains(emotionNumber)) ||
                    (message.length() < MIN_MESSAGE_LENGTH_VALUE || message.length() > MAX_MESSAGE_LENGTH_VALUE) ||
                    (messageTimeout < MIN_MESSAGE_TIMEOUT_VALUE || messageTimeout > MAX_MESSAGE_TIMEOUT_VALUE)) {
                throw new RuntimeException();
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}
