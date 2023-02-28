package org.example.database.hibernate;

import org.example.model.Saveable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class EntityManager {

    public final SessionFactory sessionFactory;// tworzymy ten obiekt w pliku hibernate.cfg.xml to połączenie z bazą danych

    protected EntityManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void persist(Saveable o)  {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(o);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }


    public void update(Saveable o) {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(o);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
}

