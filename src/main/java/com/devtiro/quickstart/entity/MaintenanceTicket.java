package com.devtiro.quickstart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class MaintenanceTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate reportedAt;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonBackReference("room-ticket")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference("student-ticket")
    private Student reportingStudent;

    public MaintenanceTicket() {}

    public MaintenanceTicket(String description, Room room) {
        this.description = description;
        this.room = room;
        this.reportedAt = LocalDate.now();
        this.status = TicketStatus.OPEN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDate reportedAt) {
        this.reportedAt = reportedAt;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Student getReportingStudent() {
        return reportingStudent;
    }

    public void setReportingStudent(Student reportingStudent) {
        this.reportingStudent = reportingStudent;
    }
}
