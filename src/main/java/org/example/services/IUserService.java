package org.example.services;

import jdk.jshell.spi.ExecutionControl;
import org.example.exceptions.UserNotExistException;
import org.example.model.User;
import org.example.model.dto.UserDTO;

import java.util.Optional;

public interface IUserService {

    Optional<User> getUserByLogin(String login);

    User persistUser(UserDTO user);

    Optional<User> getUserById(int id) throws ExecutionControl.NotImplementedException;

    User updateUser(UserDTO user,int userId) throws ExecutionControl.NotImplementedException, UserNotExistException;
}








