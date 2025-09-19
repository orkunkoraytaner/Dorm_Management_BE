package com.devtiro.quickstart.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Student extends Person {

    private String department;
    private int deskNumber;

    @ManyToOne
    @JoinColumn(name = "room_id") //foreign_key for our entity table
    private Room room;

    @ManyToMany
    @JoinTable(name = "student_roles", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Payment> payments = new ArrayList<>();

    public Student() {}

    public Student(String firstname, String lastname, String email, String department, int deskNumber) {
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


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
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

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }



}
