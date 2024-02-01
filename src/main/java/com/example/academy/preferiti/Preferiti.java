package com.example.academy.preferiti;

import com.example.academy.course.Corso;
import com.example.academy.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "preferiti")
public class Preferiti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name="preferiti_corso",
    joinColumns = @JoinColumn(name = "preferiti_id"),
            foreignKey = @ForeignKey(name = "corso_id"),
    inverseJoinColumns = @JoinColumn(name = "corso_id"),
            inverseForeignKey = @ForeignKey(name = "preferiti_id"))

    private List<Corso> corso;

}
