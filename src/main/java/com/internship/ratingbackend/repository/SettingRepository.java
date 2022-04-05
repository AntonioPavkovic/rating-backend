package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
    Optional<Setting> getSettingById(Integer id);
}
