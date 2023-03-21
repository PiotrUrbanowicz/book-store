package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="torderposition")
public class OrderPosition implements Saveable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
///zamówiona pozycja
    @ManyToOne(fetch = FetchType.EAGER)//tu był błąd - czemu nie zapisywał mi się order
    private Book book;

    private int quantity;


    public OrderPosition(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public void incrementQuantity(){
      this.quantity++;
    }
}
