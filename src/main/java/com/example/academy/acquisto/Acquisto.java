package com.example.academy.acquisto;

import com.example.academy.carrello.Carrello;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="acquisti")
public class Acquisto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate created_at;
    @ManyToOne
    @JoinColumn(name = "carrello_id")
    private Carrello carrello;
}
