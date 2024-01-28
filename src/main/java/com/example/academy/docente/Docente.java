package com.example.academy.docente;

import com.example.academy.materia.Materia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="docenti")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @ManyToMany
    @JoinTable(name = "docente_materia",
    joinColumns = @JoinColumn(name = "docente_id"),
    inverseJoinColumns = @JoinColumn(name = "materia_id"))
    private List<Materia> materia;
}
