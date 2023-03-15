package org.example.database.hibernate;

import jdk.jshell.spi.ExecutionControl;
import org.example.database.IUserDAO;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAOImpl extends EntityManager implements IUserDAO {

    public UserDAOImpl(@Autowired SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session=sessionFactory.openSession();
        Query<User> query=session.createQuery("FROM org.example.model.User WHERE login = :login",User.class);
        query.setParameter("login",login);
        Optional<User> result=Optional.empty();
        try{
        result= Optional.of(query.getSingleResult());
        }catch (Exception e){}
        session.close();
        return result;
    }

    @Override
    public void persistUser(User user) {
        super.persist(user);
        }

    @Override
    public Optional<User> getUserById(int UserId) {
        Session session=sessionFactory.openSession();
        Query<User> query=session.createQuery("FROM org.example.model.User WHERE id = :id",User.class);
        query.setParameter("id",UserId);
        Optional<User> result=Optional.empty();
        try{
            result= Optional.of(query.getSingleResult());
        }catch (Exception e){}
        session.close();
        return result;
    }

    @Override
    public void updateUser(User user) {
        super.update(user);
    }


}


