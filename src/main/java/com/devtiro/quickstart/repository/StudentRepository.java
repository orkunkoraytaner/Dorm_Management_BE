package com.devtiro.quickstart.repository;

import com.devtiro.quickstart.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
