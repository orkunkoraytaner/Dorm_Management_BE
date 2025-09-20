package com.devtiro.quickstart.services;

import com.devtiro.quickstart.entity.Room;
import com.devtiro.quickstart.exceptions.NoEmptySpaceException;
import com.devtiro.quickstart.exceptions.RoomNotFoundException;
import com.devtiro.quickstart.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            return optionalRoom.get();
        }
        else {
            throw new RoomNotFoundException("Room not found");
        }
    }

    public Room updateRoom(Long id, Room newRoom) {
        Room currentRoom = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
        currentRoom.setEmptySpace(newRoom.getEmptySpace());
        currentRoom.setRoomNumber(newRoom.getRoomNumber());
        currentRoom.setCapacity(newRoom.getCapacity());
        currentRoom.setFloor(newRoom.getFloor());
        return roomRepository.save(currentRoom);
    }

    public int getRoomFloor(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));
        return room.getFloor();
    }

    public Room reduceRoomSpaceByOne(Long roomId)
    {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));
        int currentCapacity = room.getCapacity();
        if(currentCapacity <= 0)
            throw new NoEmptySpaceException("There is no empty space in this room");
        room.setEmptySpace(currentCapacity - 1);
        return roomRepository.save(room);
    }





}
