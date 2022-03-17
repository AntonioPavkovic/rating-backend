package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
    
}
