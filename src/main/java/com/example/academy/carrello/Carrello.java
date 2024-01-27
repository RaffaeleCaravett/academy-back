package com.example.academy.carrello;

import com.example.academy.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;

@Entity
@Table(name="carrello")
@Getter
@Setter
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
