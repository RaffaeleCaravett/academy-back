package com.example.academy.carrello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello,Long> {

    Carrello findByUser_Id(long user_id);
}
