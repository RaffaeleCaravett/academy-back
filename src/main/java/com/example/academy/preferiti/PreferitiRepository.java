package com.example.academy.preferiti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferitiRepository extends JpaRepository<Preferiti,Long> {
    Preferiti findByUser_Id(long user_id);
}
