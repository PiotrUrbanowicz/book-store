package org.example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.User;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String surname;
    private String login;
    private String password;
    private User.Role role;

}
