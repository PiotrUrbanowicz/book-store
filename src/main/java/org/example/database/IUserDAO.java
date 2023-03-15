package org.example.database;

import jdk.jshell.spi.ExecutionControl;
import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    public Optional<User> getUserByLogin(String login);

    void persistUser(User user);


    Optional<User> getUserById(int id) throws ExecutionControl.NotImplementedException;

    void updateUser(User user) throws ExecutionControl.NotImplementedException;
}
