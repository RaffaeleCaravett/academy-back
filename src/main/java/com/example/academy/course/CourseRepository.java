package com.example.academy.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Corso,Long> {


    Page<Corso> findByNomeContainingIgnoreCaseAndPrezzoEqualsAndDescrizioneContainingIgnoreCaseAndDocente_IdEquals(String nome,
                             double prezzo,
                              String descrizione,
                            long docente, Pageable pageable);

    Page<Corso> findByNomeContainingIgnoreCaseAndDescrizioneContainingIgnoreCaseAndPrezzoEquals(String nome,
                                                                            String descrizione,
                                                                            double prezzo,
                                                                             Pageable pageable);

    Page<Corso> findByNomeContainingIgnoreCaseAndDescrizioneContainingIgnoreCase(String nome,
                                                             String descrizione,
                                                             Pageable pageable);

    Page<Corso> findByNomeContainingIgnoreCaseAndDescrizioneContainingIgnoreCaseAndDocente_IdEquals(String nome,
                                                                                String descrizione,
                                                                                                    long docente, Pageable pageable);

Page<Corso> findAllByNomeContainingIgnoreCase(String nome,Pageable pageable);
}
