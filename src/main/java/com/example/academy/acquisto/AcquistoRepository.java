package com.example.academy.acquisto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcquistoRepository extends JpaRepository<Acquisto,Long> {

List<Acquisto> findAllByCarrello_Id(long carrello_id);
}
