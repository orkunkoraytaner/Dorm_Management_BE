package com.devtiro.quickstart.services;

import com.devtiro.quickstart.model.Room;
import com.devtiro.quickstart.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository)
    {
        this.roomRepository = roomRepository;
    }

    public Room saveRoom(Room roam) {
        return roomRepository.save(roam);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public Room updateRoom(Long id, Room room) {
        Room room1 = roomRepository.findById(id).orElse(null);
        if(room1 != null)
        {
            // room.setRoomNumber();
        }
        return null;
    }




}
