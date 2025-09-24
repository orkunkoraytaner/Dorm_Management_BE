package com.devtiro.quickstart.controller;

import com.devtiro.quickstart.dto.RoomDto;
import com.devtiro.quickstart.entity.Room;
import com.devtiro.quickstart.entity.Student;
import com.devtiro.quickstart.services.RoomService;
import com.devtiro.quickstart.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private StudentService studentService;

    @PostMapping
    public RoomDto createRoom(@RequestBody RoomDto roomDto) {
        return roomService.saveRoom(roomDto);
    }

    @GetMapping
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomDto getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PutMapping("/{id}")
    public RoomDto updateRoom(@PathVariable Long id, @RequestBody RoomDto room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}
