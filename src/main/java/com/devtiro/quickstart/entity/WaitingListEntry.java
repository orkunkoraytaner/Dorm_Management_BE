package com.devtiro.quickstart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Locale;

@Entity
public class WaitingListEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference("student-waitlist")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonBackReference("room-waitlist")
    private Room room;

    private LocalDateTime addedAt;

    public WaitingListEntry() {}

    public WaitingListEntry(Student student, Room room) { // We are initiating it with the student and room entities because it cannot be exist without them isn't it ?
        this.student = student;
        this.room = room;
        this.addedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
