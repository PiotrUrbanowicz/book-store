package org.example.database.hibernate;

import org.example.exceptions.UserLoginExistException;
import org.example.model.Saveable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

public abstract class EntityManager {

    public final SessionFactory sessionFactory;// tworzymy ten obiekt w pliku hibernate.cfg.xml to połączenie z bazą danych

    protected EntityManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void persist(Saveable o) throws UserLoginExistException  {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(o);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            throw new UserLoginExistException();
        }
    }


    public void update(Saveable o) {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            System.out.println("1");
            session.merge(o);
            System.out.println("2");
            session.getTransaction().commit();
            System.out.println("3");
        }catch (Exception e){

            System.out.println("4");
            session.getTransaction().rollback();
            System.out.println("5");
        }finally {
            System.out.println("6");
            session.close();
            System.out.println("7");
        }
    }
}

