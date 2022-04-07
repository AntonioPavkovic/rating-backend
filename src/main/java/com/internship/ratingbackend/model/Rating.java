package com.internship.ratingbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Rating class represents rating table in database. Every rating is and will be stored
 * in this table
 *
 * @see Emotion
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "rating")
public class Rating {

    /**
     * rating ID
     */

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    /**
     * date at which rating was created
     */

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    /**
     * Foreign Key to Emotion table
     */

    @ManyToOne()
    @JoinColumn(name = "emotion_id", nullable = false)
    private Emotion emotion;

    /**
     * Constructor used in Rating Controller to create a new rating
     * @param emotion
     */

    public Rating (Emotion emotion) {
        this.createdAt = LocalDateTime.now();
        this.emotion=emotion;
    }

}
