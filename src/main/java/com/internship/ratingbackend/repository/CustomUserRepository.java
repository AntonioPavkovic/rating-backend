package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository for CustomUser
 * @see CustomUser
 */

public interface CustomUserRepository extends JpaRepository<CustomUser,Integer> {

    /**
     * Querry that find CustomUser by email
     * @param email
     * @return CustomUser
     */

    Optional<CustomUser> findCustomUsersByEmail(String email);
}
