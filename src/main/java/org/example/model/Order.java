package org.example.model;

import java.util.List;

public class Order {
    private int id;
    private int userId;
    private List<OrderPosition> positions;
   // private LocalDateTime date;
    private State state;
    private double total;

    public Order() {
    }

    public Order(int userId, List<OrderPosition> positions, State state, double total) {
        this.userId = userId;
        this.positions = positions;
       // this.state = state;
        this.total = total;
        this.state=state;
    }

    public Order(int id, int userId, List<OrderPosition> positions, State state, double total) {
        this(userId,positions,state,total);
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<OrderPosition> positions) {
        this.positions = positions;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public enum State {
        NEW,
        PAID,
        CONFIRMED,
        SENT,
        DONE
    }

}
