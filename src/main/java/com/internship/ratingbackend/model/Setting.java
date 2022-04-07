package com.internship.ratingbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Setting class represents setting table in database. There is only one setting which can be updated
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "setting")
public class Setting {

    /**
     * setting ID
     */

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id")
    private Integer id;

    /**
     * Number of emotions on the screen (min 3 max 5)
     */

    @Min(value = 3, message = "Minimum value for emotions number is 3")
    @Max(value = 5, message = "Maximum value for emotions number is 5")
    @NotNull
    @Column(name = "emotion_number", nullable = false, columnDefinition = "SMALLINT default 3")
    private Integer emotionNumber=3;

    /**
     * 'Thank you' message that is presented on the screen after voting
     */

    @Size(min = 3,max = 128,message = "Message length value must be in bounds 3-128 chars")
    @Column(name = "message", columnDefinition = "VARCHAR(128) default 'Thank you for rating.'")
    private String message="Thank you for rating.";

    /**
     * message timeout - how long will the message appear for, after voting
     */

    @Min(value = 0, message = "Minimum value for message timeout is 0")
    @Max(value = 10, message = "Maximum value for message timeout is 10")
    @NotNull
    @Column(name = "message_timeout", nullable = false, columnDefinition = "SMALLINT default 5")
    private Integer messageTimeout=5;


}
