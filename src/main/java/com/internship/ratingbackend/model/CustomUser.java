package com.internship.ratingbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * CustomUser class represents an entity. Every user is stored in this table
 *
 * @see CustomUserRole
 */

@Entity
@Table(name = "custom_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomUser {

    /**
     * Custom user id
     */

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id", nullable = false)
    private Integer id;


    /**
     * Custom user email
     */

    @Column(name = "email", nullable = false)
    private String email;


    /**
     * custom user role
     */

    @Column(name = "custom_user_role")
    @Enumerated(EnumType.STRING)
    private CustomUserRole customUserRole;


}
