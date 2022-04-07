package com.internship.ratingbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Emotion_setting class that represents emotion_setting entity. Every emotion_setting is stored
 * in this table
 *
 * @see Emotion
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emotion_setting")
public class EmotionSetting {

    /**
     * emotion_setting id
     */

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Foreign key to Emotion table
     */

    @ManyToOne()
    @JoinColumn(name = "emotion_id", nullable = false)
    Emotion emotion;

    /**
     * emotion value (1 - 5)
     */

    @Column(name = "emotion_value", nullable = false)
    private Integer emotionValue;
}
