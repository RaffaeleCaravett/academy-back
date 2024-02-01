package com.example.academy.docente;

import com.example.academy.course.Corso;
import com.example.academy.materia.Materia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "docente_materia",
    joinColumns = @JoinColumn(name = "docente_id"),
            foreignKey = @ForeignKey(name = "materia_id"),
    inverseJoinColumns = @JoinColumn(name = "materia_id"),
            inverseForeignKey = @ForeignKey(name = "docente_id"))
    private List<Materia> materia;
    @JsonIgnore
    @ManyToMany(mappedBy = "docente")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Corso> corso;
}
