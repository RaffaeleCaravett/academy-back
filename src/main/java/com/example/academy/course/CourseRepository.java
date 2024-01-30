package com.example.academy.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Corso,Long> {


    List<Corso> findByNomeContainingAndPrezzoEqualsAndDescrizioneContainingAndDocente_IdAndMateria_Id(String nome,Double prezzo,String descrizione, long docente_id,long materia_id);
}
