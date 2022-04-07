package com.internship.ratingbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Emotion class represents an entity. Every emotion is stored in this table
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "emotion")
public class Emotion {

    /**
     * emotion Id
     */

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Emotion color
     */

    @Column(name = "color", nullable = false)
    private String color;

    /**
     * emotion name
     */

    @Column(name = "name", nullable = false)
    private String name;

}
