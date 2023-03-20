package org.example.services.impl;

import jdk.jshell.spi.ExecutionControl;
import org.example.database.IOrderPositionDAO;
import org.example.model.OrderPosition;
import org.example.services.IOrderPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderPositionServiceImpl implements IOrderPositionService {

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Override
    public Optional<OrderPosition> getOrderPositionById(int id) throws ExecutionControl.NotImplementedException {
        return this.orderPositionDAO.getOrderPositionById(id);
    }
}
