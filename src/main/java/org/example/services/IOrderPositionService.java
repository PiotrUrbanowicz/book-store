package org.example.services;

import jdk.jshell.spi.ExecutionControl;
import org.example.database.IOrderPositionDAO;
import org.example.model.OrderPosition;

import java.util.Optional;

public interface IOrderPositionService {

    Optional<OrderPosition> getOrderPositionById(int id) throws ExecutionControl.NotImplementedException;
}
