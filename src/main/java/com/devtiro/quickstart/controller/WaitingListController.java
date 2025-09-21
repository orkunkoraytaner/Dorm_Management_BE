package com.devtiro.quickstart.controller;

import com.devtiro.quickstart.entity.Student;
import com.devtiro.quickstart.entity.WaitingListEntry;
import com.devtiro.quickstart.services.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms/{roomId}/waiting-list")
public class WaitingListController {

    private final WaitingListService waitingListService;

    @Autowired
    public WaitingListController(WaitingListService waitingListService) {
        this.waitingListService = waitingListService;
    }


    @PostMapping
    ResponseEntity<WaitingListEntry> addStudentToWaitingList(@PathVariable("roomId") Long roomId, @RequestBody Student student)
    {
        Long studentID = student.getId();
        return waitingListService.addStudentToWaitingList(roomId, studentID)
                .map(entry -> new ResponseEntity<>(entry, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<WaitingListEntry>> getWaitingListForRoom(@PathVariable Long roomId) {
        List<WaitingListEntry> waitingList = waitingListService.getWaitingListForRoom(roomId);
        return ResponseEntity.ok(waitingList);
    }

    @PostMapping("/next")
    public ResponseEntity<Student> getNextStudentInLine(@PathVariable Long roomId)
    {
        return waitingListService.getNextStudentInLine(roomId)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
