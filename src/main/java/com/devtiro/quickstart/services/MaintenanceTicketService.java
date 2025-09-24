package com.devtiro.quickstart.services;

import com.devtiro.quickstart.entity.MaintenanceTicket;
import com.devtiro.quickstart.entity.Room;
import com.devtiro.quickstart.entity.Student;
import com.devtiro.quickstart.entity.TicketStatus;
import com.devtiro.quickstart.exceptions.RoomNotFoundException;
import com.devtiro.quickstart.exceptions.StudentNotFoundException;
import com.devtiro.quickstart.repository.MaintenanceTicketRepository;
import com.devtiro.quickstart.repository.RoomRepository;
import com.devtiro.quickstart.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceTicketService {

    private final RoomService roomService;
    private MaintenanceTicketRepository maintenanceTicketRepository;
    private RoomRepository roomRepository;
    private final ActivityLogService activityLogService;
    private StudentRepository studentRepository;

    @Autowired
    public MaintenanceTicketService(MaintenanceTicketRepository maintenanceTicketRepository, RoomRepository roomRepository, RoomService roomService, ActivityLogService activityLogService,StudentRepository studentRepository)
    {
        this.maintenanceTicketRepository = maintenanceTicketRepository;
        this.roomRepository = roomRepository;
        this.roomService = roomService;
        this.activityLogService = activityLogService;
        this.studentRepository = studentRepository;
    }

    public List<MaintenanceTicket> getAllMaintenanceTicketsForRoom(Long roomId)
    {
        return maintenanceTicketRepository.findByRoomId(roomId);
    }

    public MaintenanceTicket createTicket(Long studentId, MaintenanceTicket maintenanceTicket)
    {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
        Long roomId = student.getRoom().getId();
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        maintenanceTicket.setRoom(room);
        maintenanceTicket.setReportingStudent(student);
        maintenanceTicket.setStatus(TicketStatus.OPEN);
        activityLogService.addActivity(studentId,"Maintenance Ticket Created: " + maintenanceTicket.getDescription() + "for room: " + maintenanceTicket.getRoom());
        return maintenanceTicketRepository.save(maintenanceTicket);

    }

    public Optional<MaintenanceTicket> updateTicketStatus(Long ticketId, TicketStatus ticketStatus)
    {
        Optional<MaintenanceTicket> ticketOptional = maintenanceTicketRepository.findById(ticketId);
        if(ticketOptional.isPresent())
        {
            MaintenanceTicket newTicket = ticketOptional.get();
            newTicket.setStatus(ticketStatus);
            return Optional.of(maintenanceTicketRepository.save(newTicket));
        }
        return Optional.empty();
    }

    public boolean deleteTicket(Long maintenanceTicketId)
    {
        if(maintenanceTicketRepository.existsById(maintenanceTicketId))
        {
            maintenanceTicketRepository.deleteById(maintenanceTicketId);
            return true;
        }
        return false;
    }
}
