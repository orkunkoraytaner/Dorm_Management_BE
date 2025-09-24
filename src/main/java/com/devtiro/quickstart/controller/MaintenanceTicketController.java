package com.devtiro.quickstart.controller;

import com.devtiro.quickstart.entity.MaintenanceTicket;
import com.devtiro.quickstart.entity.Room;
import com.devtiro.quickstart.entity.Student;
import com.devtiro.quickstart.entity.TicketStatus;
import com.devtiro.quickstart.services.MaintenanceTicketService;
import com.devtiro.quickstart.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms/{studentId}/tickets")
public class MaintenanceTicketController {

    private final MaintenanceTicketService maintenanceTicketService;
    private final StudentService studentService;

    @Autowired
    public MaintenanceTicketController(MaintenanceTicketService maintenanceTicketService, StudentService studentService) {
        this.maintenanceTicketService = maintenanceTicketService;
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<MaintenanceTicket> createTicketForRoom(@PathVariable Long studentId, @RequestBody MaintenanceTicket ticket)
    {
        MaintenanceTicket createdTicket = maintenanceTicketService.createTicket(studentId, ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED); //koray_check

    }

    @GetMapping
    public ResponseEntity<List<MaintenanceTicket>> getAllTicketsForRoom(@PathVariable Long roomId)
    {
        List<MaintenanceTicket> allTicketsForRoom = maintenanceTicketService.getAllMaintenanceTicketsForRoom(roomId);
        return ResponseEntity.ok(allTicketsForRoom);
    }

    @PutMapping("/{ticketId}/status")
    public ResponseEntity<MaintenanceTicket> updateTicketStatus(@PathVariable Long ticketId, @RequestBody TicketStatus ticket)
    {
        return maintenanceTicketService.updateTicketStatus(ticketId, ticket)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId)
    {
        boolean deleted = maintenanceTicketService.deleteTicket(ticketId);
        if (deleted)
        {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
