package org.example.database;

import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    public Optional<User> getUserByLogin(String login);

    void persistUser(User user);
}
