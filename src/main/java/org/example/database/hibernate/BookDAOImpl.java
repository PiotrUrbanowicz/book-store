package org.example.database.hibernate;

import org.example.database.IBookDAO;
import org.example.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Repository
public class BookDAOImpl extends EntityManager implements IBookDAO {

    public BookDAOImpl(@Autowired SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void persistBook(Book book) {
        super.persist(book);
    }
    @Override
    public void updateBook(Book book) {
        super.update(book);
    }

    @Override
    public List<Book> getBooks() {
       Session session=sessionFactory.openSession();
        Query<Book> query = session.createQuery("From org.example.model.Book", Book.class);
        List<Book> result=query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Book> getBooksByPattern(String pattern) {
        Session session=sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM org.example.model.Book " +
                "WHERE title LIKE :pattern OR author LIKE :pattern", Book.class);
        query.setParameter("pattern","%"+pattern+"%");
        List<Book> result=query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        Session session=sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM org.example.model.Book WHERE id =:id", Book.class);
        query.setParameter("id",id);
        Optional<Book> result=Optional.empty();
        try {
            result= Optional.of(query.getSingleResult());
        }catch (Exception e){}
        session.close();
        return result;
    }
}
