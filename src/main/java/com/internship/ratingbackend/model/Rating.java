package com.internship.ratingbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name = "emotion_id", nullable = false)
    private Emotion emotion;

    public Rating (Emotion emotion) {
        this.createdAt = LocalDateTime.now();
        this.emotion=emotion;
    }

}
