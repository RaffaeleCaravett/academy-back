package com.example.academy.carrello;

import com.example.academy.acquisto.Acquisto;
import com.example.academy.course.Corso;
import com.example.academy.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name="carrello")
@Getter
@Setter
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "carrello_corso",
            joinColumns = @JoinColumn(name = "carrello_id"),
            foreignKey = @ForeignKey(name = "corso_id"),
    inverseJoinColumns = @JoinColumn(name = "corso_id"),
            inverseForeignKey = @ForeignKey(name = "carrello_id"))
    private List<Corso> corso;
    @OneToMany(mappedBy = "carrello",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
private List<Acquisto> acquisto;
}
