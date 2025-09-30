package com.devtiro.quickstart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.*;


@Entity
public class Student extends Person {

    @NotBlank(message = "Department is mandatory")
    @Enumerated(EnumType.STRING)
    private StudentMajor department;
    private int deskNumber;

    @ManyToOne
    @JoinColumn(name = "room_id")//foreign_key for our entity table
    @JsonBackReference("room-student")
    private Room room;

    @NotBlank(message = "Role assign is mandatory please choose one")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("student-payment")
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "reportingStudent")
    @JsonManagedReference("student-ticket")
    private List<MaintenanceTicket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("student-waitlist")
    private List<WaitingListEntry> writingListEntries = new LinkedList<>();



    public Student() {}

    public Student(String firstname, String lastname, String email, StudentMajor department, int deskNumber) {
        setFirstName(firstname); // Student class directly uses the Person class methods
        setLastName(lastname);
        setEmail(email);
        this.department = department;
        this.deskNumber = deskNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", department='" + department + '\'' +
                ", deskNumber=" + deskNumber +
                ", room=" + room +
                '}';
    }


    public StudentMajor getDepartment() {
        return department;
    }

    public void setDepartment(StudentMajor department) {
        this.department = department;
    }

    public int getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public @NotBlank(message = "Role assign is mandatory please choose one") Role getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role assign is mandatory please choose one") Role role) {
        this.role = role;
    }

    public List<MaintenanceTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<MaintenanceTicket> tickets) {
        this.tickets = tickets;
    }

    public List<WaitingListEntry> getWritingListEntries() {
        return writingListEntries;
    }

    public void setWritingListEntries(List<WaitingListEntry> writingListEntries) {
        this.writingListEntries = writingListEntries;
    }
}
