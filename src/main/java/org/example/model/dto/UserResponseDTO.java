package org.example.model.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.example.model.User;


@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {

    private int id;
    private String name;
    private String surname;
    private String login;
    private User.Role role;
    private String orders;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.login = user.getLogin();
        this.role = user.getRole();
        this.orders="http://localhost:8075/api/v1/order?userId="+user.getId();
    }

}
