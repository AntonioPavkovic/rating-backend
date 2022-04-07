package com.internship.ratingbackend.repository;

import com.internship.ratingbackend.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository for Setting
 *
 * @see Setting
 */

public interface SettingRepository extends JpaRepository<Setting, Integer> {

    /**
     * Query setting by id
     * @param id
     * @return Setting by id
     */

    Optional<Setting> getSettingById(Integer id);
}
