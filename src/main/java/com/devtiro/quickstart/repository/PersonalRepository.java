package com.devtiro.quickstart.repository;

import com.devtiro.quickstart.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
