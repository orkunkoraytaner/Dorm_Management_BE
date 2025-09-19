package com.devtiro.quickstart.repository;

import com.devtiro.quickstart.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesRepository extends JpaRepository<Roles, Long> {
}
