package org.example.database.hibernate;

import jdk.jshell.spi.ExecutionControl;
import org.example.database.IOrderPositionDAO;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderPositionDAOImpl implements IOrderPositionDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistOrderPosition(OrderPosition orderPosition, int orderId)  {
        System.out.println("Not Implemented");
    }

    @Override
    public List<OrderPosition> getOrderPositionByOrderId(int id) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not Implemented");
    }

    @Override
    public Optional<OrderPosition> getOrderPositionById(int id) {
        Session session=sessionFactory.openSession();
        Query<OrderPosition> query=session.createQuery("FROM org.example.model.OrderPosition WHERE id = :id",OrderPosition.class);
        query.setParameter("id",id);
        Optional<OrderPosition> result=Optional.empty();
        try{
            result= Optional.of(query.getSingleResult());
        }catch (Exception ignored){}
        session.close();
        return result;

    }
}
