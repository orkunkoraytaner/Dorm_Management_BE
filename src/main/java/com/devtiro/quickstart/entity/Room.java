package com.devtiro.quickstart.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    private int capacity;
    private int floor;
    private int emptySpace;

    @OneToMany(mappedBy = "room")
    @JsonManagedReference("room-student")
    private List<Student> students;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("room-ticket")
    private List<MaintenanceTicket> maintenanceTickets;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("room-waitlist")
    private List<WaitingListEntry> waitingList = new LinkedList<>();

    public Room() {}

    public Room(int roomNumber, int capacity)
    {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.emptySpace = capacity;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getEmptySpace() {
        return emptySpace;
    }

    public void setEmptySpace(int emptySpace) {
        this.emptySpace = emptySpace;
    }

    public List<WaitingListEntry> getWaitingListEntries() {
        return waitingList;
    }

    public void setWaitingListEntries(List<WaitingListEntry> waitingListEntries) {
        this.waitingList = waitingList;
    }

    public List<MaintenanceTicket> getMaintenanceTickets() {
        return maintenanceTickets;
    }

    public void setMaintenanceTickets(List<MaintenanceTicket> maintenanceTickets) {
        this.maintenanceTickets = maintenanceTickets;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}