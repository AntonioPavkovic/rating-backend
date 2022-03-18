package com.internship.ratingbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "emotion")
public class Emotion {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "name", nullable = false)
    private String name;

}
