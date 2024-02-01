package com.example.academy.course;

import com.example.academy.carrello.Carrello;
import com.example.academy.docente.Docente;
import com.example.academy.materia.Materia;
import com.example.academy.preferiti.Preferiti;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name="corso")
@Getter
@Setter
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private double prezzo;
    private String descrizione;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name="corso_docente",
    joinColumns = @JoinColumn(name = "corso_id"),
            foreignKey = @ForeignKey(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "docente_id"),
    inverseForeignKey = @ForeignKey(name = "corso_id"))
    private List<Docente> docente;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name="corso_materia",
            joinColumns = @JoinColumn(name = "corso_id"),
            foreignKey = @ForeignKey(name = "materia_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id"),
            inverseForeignKey = @ForeignKey(name = "corso_id")
    )
    private List<Materia> materia;
@ManyToMany(mappedBy = "corso")
@OnDelete(action = OnDeleteAction.CASCADE)
@JsonIgnore
private List<Carrello> carrello;
@ManyToMany(mappedBy = "corso")
@OnDelete(action = OnDeleteAction.CASCADE)
@JsonIgnore
    private List<Preferiti> preferiti;
}
