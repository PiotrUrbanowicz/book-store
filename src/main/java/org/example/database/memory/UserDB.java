package org.example.database.memory;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.database.IUserDAO;
import org.example.exceptions.UserLoginExistException;
import org.example.model.User;
import org.example.sequence.IIdSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDB implements IUserDAO {

    private List<User> users=new ArrayList<>();

    IIdSequence userIdSequence;
    public UserDB(@Autowired IIdSequence userIdSequence) {
        this.userIdSequence=userIdSequence;
    users.add(new User( this.userIdSequence.getId(),"Mikolaj", "Janczyk",
            "admin", "21232f297a57a5a743894a0e4a801fc3", User.Role.ADMIN));//admin
    users.add(new User( this.userIdSequence.getId(),"Piotr", "Urbanowicz",
            "janusz123", "8f6de86901ba906047425ff9f71550dd", User.Role.USER));//janusz123

    }

    @Override
    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        for (User user: this.users) {
            if(user.getLogin().equals(login)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }


    @Override
    public void persistUser(User user) {
        if(getUserByLogin(user.getLogin()).isPresent()){
            throw new UserLoginExistException();
        }else{
            user.setId(this.userIdSequence.getId());
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));//bo zapisało by się nie zachaszowane
            users.add(user);
        }

    }
}
