package com.devtiro.quickstart.repository;

import com.devtiro.quickstart.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
