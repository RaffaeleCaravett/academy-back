package com.example.academy.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Corso,Long> {
    //@Query("SELECT c FROM Corso c " +
      //      "LEFT JOIN c.docente d " +
        //    "WHERE :param MEMBER OF [c.nome, CAST(c.prezzo AS string), c.descrizione, d.nome, d.materia.nome]")
  //  List<Corso> findCoursesByParam(@Param("param") String param);
}
