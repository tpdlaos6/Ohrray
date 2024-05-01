package com.ohrray.repository;

import com.ohrray.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionsRepository extends JpaRepository<Options, Long> {
    Optional<Options> findBySizeAndColor(int size, String color);
}
