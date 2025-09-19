package com.devtiro.quickstart.services;

import com.devtiro.quickstart.entity.MaintenanceTicket;
import com.devtiro.quickstart.entity.Room;
import com.devtiro.quickstart.entity.TicketStatus;
import com.devtiro.quickstart.repository.MaintenanceTicketRepository;
import com.devtiro.quickstart.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceTicketService {

    private final RoomService roomService;
    private MaintenanceTicketRepository maintenanceTicketRepository;
    private RoomRepository roomRepository;

    @Autowired
    public MaintenanceTicketService(MaintenanceTicketRepository maintenanceTicketRepository, RoomRepository roomRepository, RoomService roomService)
    {
        this.maintenanceTicketRepository = maintenanceTicketRepository;
        this.roomRepository = roomRepository;
        this.roomService = roomService;
    }

    public List<MaintenanceTicket> getAllMaintenanceTicketsForRoom(Long roomId)
    {
        return maintenanceTicketRepository.findByRoomId(roomId);
    }

    public Optional<MaintenanceTicket> createTicket(Long roomId, MaintenanceTicket maintenanceTicket)
    {
        Optional<Room> room = roomRepository.findById(roomId);
        if(room.isPresent())
        {
            maintenanceTicket.setRoom(room.get());
            return Optional.of(maintenanceTicketRepository.save(maintenanceTicket));
        }
        return Optional.empty();
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
