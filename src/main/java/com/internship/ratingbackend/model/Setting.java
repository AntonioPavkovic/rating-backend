package com.internship.ratingbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "setting")
public class Setting {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id")
    private Integer id;


    @Column(name = "emotion_number",nullable = false,columnDefinition = "SMALLINT")
    private Integer emotionNumber;


    @Column(name = "message", nullable = false, columnDefinition = "VARCHAR(128)")
    private String message;

    @Column(name = "message_timeout", nullable = false, columnDefinition = "SMALLINT")
    private Integer messageTimeout;


    public Setting(Integer emotionNumber, String message, Integer messageTimeout) {
        this.emotionNumber = emotionNumber;
        this.message = message;
        this.messageTimeout = messageTimeout;
    }
}
