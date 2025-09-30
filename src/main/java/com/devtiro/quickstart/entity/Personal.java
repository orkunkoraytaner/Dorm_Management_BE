package com.devtiro.quickstart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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

    @NotBlank(message = "Choose a role please")
    @Enumerated(EnumType.STRING)
    private Role role;

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

    public @NotBlank(message = "Choose a role please") Role getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Choose a role please") Role role) {
        this.role = role;
    }
}
