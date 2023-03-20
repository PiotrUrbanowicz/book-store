package org.example.controllers.rest;

import jdk.jshell.spi.ExecutionControl;
import org.example.database.IOrderPositionDAO;
import org.example.model.Order;
import org.example.model.OrderPosition;
import org.example.model.dto.OrderPositionDTO;
import org.example.services.IOrderPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/orderposition")
public class RestApiPositionController {

    @Autowired
    IOrderPositionService orderPositionService;

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderPositionDTO> getOrdersById(@PathVariable int id) throws ExecutionControl.NotImplementedException {
        Optional<OrderPosition> orderPositionBox = this.orderPositionService.getOrderPositionById(id);

        return orderPositionBox
                .map(op -> ResponseEntity.status(HttpStatus.OK).body(new OrderPositionDTO(op)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
