package org.example.model.dto;

import jakarta.persistence.*;
import org.example.model.Order;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO {

    private int id;
    private String name;
    private String surname;
    private String login;
    private User.Role role;
    private String orders;

    public UserResponseDTO() {
    }

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.login = user.getLogin();
        this.role = user.getRole();
        this.orders="http://localhost:8075/api/v1/order?userId="+user.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}
