package com.example.academy.course;

import com.example.academy.docente.Docente;
import com.example.academy.materia.Materia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @ManyToMany
    @JoinTable(name="corso_docente",
    joinColumns = @JoinColumn(name = "corso_id"),
    inverseJoinColumns = @JoinColumn(name = "docente_id"))
    private List<Docente> docente;
    @ManyToMany
    @JoinTable(name="corso_materia",
            joinColumns = @JoinColumn(name = "corso_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id"))
    private List<Materia> materia;

}
