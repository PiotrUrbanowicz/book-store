package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name="torder")
public class Order implements Saveable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    //cascade jeśli zapiszesz order w bazie to zapiszą się też wrzystkie OrderPozycje w bazie!!!
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<OrderPosition> positions;

    private LocalDateTime date;
    @Enumerated(value = EnumType.STRING)
    private State state;
    private double total;

    public Order() {
    }

    public Order(User user, Set<OrderPosition> positions, LocalDateTime date, State state, double total) {
        this.user = user;
        this.positions = positions;
        this.date = date;
        this.state = state;
        this.total = total;
    }

    public Order(int id, User user, Set<OrderPosition> positions, LocalDateTime date, State state, double total) {
        this(user,positions,date,state,total);
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        System.out.println(this.toString());
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderPosition> getPositions() {
        return positions;
    }

    public void setPositions(Set<OrderPosition> positions) {
        this.positions = positions;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", positions=" + positions +
                ", date=" + date +
                ", state=" + state +
                ", total=" + total +
                '}';
    }

    public enum State {
        NEW,
        PAID,
        CONFIRMED,
        SENT,
        DONE
    }

}
