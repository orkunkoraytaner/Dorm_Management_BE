package com.devtiro.quickstart.repository;

import com.devtiro.quickstart.entity.MaintenanceTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceTicketRepository extends JpaRepository<MaintenanceTicket,Long>
{
    List<MaintenanceTicket> findByRoomId(Long roomId);
}
