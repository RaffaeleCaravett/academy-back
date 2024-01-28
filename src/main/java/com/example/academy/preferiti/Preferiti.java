package com.example.academy.preferiti;

import com.example.academy.course.Corso;
import com.example.academy.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "preferiti")
public class Preferiti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(name="preferiti_corso",
    joinColumns = @JoinColumn(name = "preferiti_id"),
    inverseJoinColumns = @JoinColumn(name = "corso_id"))
    private List<Corso> corso;

}
