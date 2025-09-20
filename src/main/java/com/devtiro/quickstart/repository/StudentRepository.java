package com.devtiro.quickstart.repository;

import com.devtiro.quickstart.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByRoomId(Long id);


}
