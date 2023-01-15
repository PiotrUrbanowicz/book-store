package org.example.services.impl;



import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.database.IUserDAO;
import org.example.model.User;
import org.example.services.IAuthenticationService;
import org.example.sessionObject.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;
    @Resource
    SessionObject sessionObject;

    @Override
    public boolean authenticate(String login, String password) {

        Optional<User> userBox=this.userDAO.getUserByLogin(login);
        if(userBox.isEmpty() || !userBox.get().getPassword().equals(DigestUtils.md5Hex(password))){
            return false;
        }
        userBox.get().setPassword(null);
        this.sessionObject.setUser(userBox.get());
        return true;
    }


    @Override
    public void registerUser(User user) {
        user.setRole(User.Role.USER);
        this.userDAO.persistUser(user);
    }

    @Override
    public void logout() {
        this.sessionObject.setUser(null);
    }
}
