package com.devtiro.quickstart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Personal extends Person {

    private String phone;

    public Personal() {
    }

    public Personal(String firstName, String lastName, String email, String phone) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        this.phone = phone;
    }

    @ManyToMany
    @JoinTable(name = "personal_roles", joinColumns = @JoinColumn(name = "personal_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    @Override
    public String toString() {
        return "Personal{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
