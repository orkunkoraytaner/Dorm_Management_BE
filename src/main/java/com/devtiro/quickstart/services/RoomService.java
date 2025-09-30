package com.devtiro.quickstart.services;

import com.devtiro.quickstart.dto.RoomDto;
import com.devtiro.quickstart.entity.Room;
import com.devtiro.quickstart.exceptions.NoEmptySpaceException;
import com.devtiro.quickstart.exceptions.RoomNotFoundException;
import com.devtiro.quickstart.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public RoomDto saveRoom(RoomDto roomDto) {
        Room entityRoom = dtoToEntity(roomDto);
        roomRepository.save(entityRoom);
        return entityToDto(entityRoom);

    }

    public RoomDto entityToDto(Room room)
    {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setCapacity(room.getCapacity());
        return roomDto;
    }

    public Room dtoToEntity(RoomDto roomDto)
    {
        Room roomEntity = new Room(roomDto.getRoomNumber(), roomDto.getCapacity());
        roomEntity.setFloor(calculateFloorFromRoomNumber(roomDto.getRoomNumber()));
        roomEntity.setId(roomDto.getId());
        return roomEntity;
    }

    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomDto> roomDtos = new ArrayList<>();
        for (Room room : rooms) {
            RoomDto roomDto = entityToDto(room);
            roomDtos.add(roomDto);
        }
        return roomDtos;
    }

    public RoomDto getRoomById(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            RoomDto roomDto = entityToDto(optionalRoom.get());
            return roomDto;
        }
        else {
            throw new RoomNotFoundException("Room not found");
        }
    }

    public RoomDto updateRoom(Long id, RoomDto dtoRoom) {
        Room entityRoom = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room not found"));
        entityRoom.setRoomNumber(dtoRoom.getRoomNumber());
        entityRoom.setCapacity(dtoRoom.getCapacity());
        entityRoom.setFloor(calculateFloorFromRoomNumber(dtoRoom.getRoomNumber()));
        Room updatedRoom = roomRepository.save(entityRoom);
        return entityToDto(updatedRoom);

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
        int emptySpace = room.getEmptySpace();
        if(emptySpace <= 0)
            throw new NoEmptySpaceException("There is no empty space in this room"); // everything stops after this stage
        room.setEmptySpace(emptySpace - 1);
        return roomRepository.save(room);
    }

    public List<RoomDto> returnAllEmptyRooms() //this is very unefficient method because you basically fetch the whole rooms and trying to find empty ones one by one
    {
        List<Room> rooms = roomRepository.findAll();
        List<RoomDto> emptyRoomDtos = new ArrayList<>();
        for (Room room : rooms) {
            if(room.getEmptySpace() > 0)
            {
                emptyRoomDtos.add(entityToDto(room));
            }
        }
        return emptyRoomDtos;
    }

    public int calculateFloorFromRoomNumber(int roomNumber)
    {
        if(roomNumber <= 0 )
        {
            return 0;
        }
        while(roomNumber>=10)
        {
            roomNumber = roomNumber / 10;
        }
        return roomNumber;
    }





}
