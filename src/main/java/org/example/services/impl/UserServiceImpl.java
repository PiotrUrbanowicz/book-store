package org.example.services.impl;

import jdk.jshell.spi.ExecutionControl;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.database.IUserDAO;
import org.example.exceptions.UserNotExistException;
import org.example.model.User;
import org.example.model.dto.UserDTO;
import org.example.services.IUserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<User> getUserByLogin(String login) {

        return this.userDAO.getUserByLogin(login);


    }

    @Override
    public User persistUser(UserDTO userDTO) {
        User user=new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setLogin(userDTO.getLogin());
        user.setPassword(DigestUtils.md5Hex(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        this.userDAO.persistUser(user);
        return user;
    }

    @Override
    public Optional<User> getUserById(int id) throws ExecutionControl.NotImplementedException {
        return this.userDAO.getUserById(id);
    }

    @Override
    public User updateUser(UserDTO userDTO,int userId) throws ExecutionControl.NotImplementedException, UserNotExistException {

        Session session=sessionFactory.openSession();
        Query<User> query=session.createQuery("FROM org.example.model.User WHERE id = :id",User.class);
        query.setParameter("id",userId);
      User user=null;
        try{
            user= query.getSingleResult();
        }catch (Exception e){
            session.close();
            throw new UserNotExistException();
        }

        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setLogin(userDTO.getLogin());
        user.setPassword(DigestUtils.md5Hex(userDTO.getPassword()));
        user.setRole(userDTO.getRole());


        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        session.close();

        user.getOrders().forEach(o->o.setUser(null));
            return user;
    }
}
