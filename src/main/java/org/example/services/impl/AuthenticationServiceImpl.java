package org.example.services.impl;



import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.database.IUserDAO;
import org.example.model.User;
import org.example.model.sessionObject.SessionObject;
import org.example.services.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
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
        /*
        User.UserBuilder userBuilder=new User.UserBuilder();
        User userClone = userBuilder.clone(userBox.get());
        userClone.setPassword(null);
        this.sessionObject.setUser(userClone);
        */
        this.sessionObject.setUser(
                new User.UserBuilder()
                .clone(userBox.get())
                        .password(null)
                        .build()
        );
        return true;
        //nie jest źle ale jest już skomplikowanie gdybyśmy zrobili to w mniej złorzony sposób to też ok
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
