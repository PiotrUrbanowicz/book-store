package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="torder")
public class Order implements Saveable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    //cascade jeśli zapiszesz order w bazie to zapiszą się też wrzystkie OrderPozycje w bazie!!!
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderPosition> positions = new ArrayList<>();

    private LocalDateTime date;
    @Enumerated(value = EnumType.STRING)
    private State state;
    private double total;



    public Order(User user, List<OrderPosition> positions, LocalDateTime date, State state, double total) {
        this.user = user;
        this.positions = positions;
        this.date = date;
        this.state = state;
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
