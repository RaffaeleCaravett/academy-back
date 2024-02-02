package com.example.academy.acquisto;

import com.example.academy.carrello.Carrello;
import com.example.academy.carrello.CarrelloRepository;
import com.example.academy.payloads.entities.AcquistoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AcquistoService {

    @Autowired
    AcquistoRepository acquistoRepository;
@Autowired
    CarrelloRepository carrelloRepository;
    public Acquisto save(AcquistoDTO acquistoDTO){

        Acquisto acquisto = new Acquisto();

        acquisto.setCreated_at(LocalDate.now());
        Carrello carrello = carrelloRepository.findById(acquistoDTO.carrello_id()).get();
        acquisto.setCarrello(carrello);

        return acquistoRepository.save(acquisto);
    }
    public List<Acquisto> getAllByCarrello_id(long carrello_id){
        return acquistoRepository.findAllByCarrello_Id(carrello_id);
    }

    public List<Acquisto> getAll(){
        return acquistoRepository.findAll();
    }
}
