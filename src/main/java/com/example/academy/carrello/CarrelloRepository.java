package com.example.academy.carrello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello,Long> {

    Optional<Carrello> findByUser_Id(long user_id);
}
