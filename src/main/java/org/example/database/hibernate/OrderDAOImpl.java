package org.example.database.hibernate;

import jakarta.persistence.NoResultException;
import jdk.jshell.spi.ExecutionControl;
import org.example.database.IOrderDAO;
import org.example.model.Order;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistOrder(Order order) {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(order);
             session.refresh(order.getUser());//sprawiam że dane do usera zamawiającego ten order są zaciągane z bazy. Wypełniamy obiekt usera danymi.
             order.getUser().getOrders().add(order);// dodajemy mu nowy order
             session.merge(order.getUser()); // zapisujemy uaktualnionego usera w bazie danych
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public void updateOrder(Order order) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Niepotrzebna metoda !!");
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
//        Session session = this.sessionFactory.openSession();
//        Query<Order> query = session.createQuery(
//                "FROM org.example.model.Order WHERE user_id = :userId",
//                Order.class);
//        query.setParameter("userId", userId);
//        List<Order> orders = query.getResultList();
//        session.close();
//        return orders;


        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(
                "FROM org.example.model.User WHERE id = :u_id",
                User.class);
        query.setParameter("u_id", userId);
        ArrayList<Order> result = new ArrayList<>();
        try {
            User user = query.getSingleResult();
            result = new ArrayList<>(user.getOrders());//dopiero tu się zaciągają ordery bo są zaciągane LAZY
        } catch (NoResultException ignored) {}
        session.close();
        return result;
    }

    @Override
    public Optional<Order> getOrderById(int orderId){
        Session session=this.sessionFactory.openSession();
        Query<Order> query=session.createQuery("From org.example.model.Order WHERE id=:id",Order.class);
        query.setParameter("id",orderId);

        Optional<Order> result=Optional.empty();
        try {
            result = Optional.ofNullable(query.getSingleResult());
        }catch (NoResultException ignored){}
        session.close();
        return result;
    }
}
