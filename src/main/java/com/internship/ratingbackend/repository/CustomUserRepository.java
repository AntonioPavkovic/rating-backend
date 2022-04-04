package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomUserRepository extends JpaRepository<AppUser,Integer> {
    Optional<AppUser> findCustomUsersByEmail(String email);
}
