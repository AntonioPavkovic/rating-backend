package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomUserRepository extends JpaRepository<CustomUser,Integer> {
    Optional<CustomUser> findCustomUsersByEmail(String email);
}
