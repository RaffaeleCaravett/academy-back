package com.example.academy.carrello;

import com.example.academy.course.Corso;
import com.example.academy.course.CourseRepository;
import com.example.academy.exception.BadRequestException;
import com.example.academy.payloads.entities.CarrelloDTO;
import com.example.academy.user.UserRepository;
import com.fasterxml.jackson.databind.JsonSerializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarrelloService {
    @Autowired
    CarrelloRepository carrelloRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;

    public Carrello save(CarrelloDTO carrelloDTO){
        Carrello carrello = new Carrello();
        carrello.setUser(userRepository.findById(carrelloDTO.user_id()).get());
        List<Corso> corsi = new ArrayList<>();

        for(Long l : carrelloDTO.corso_id()){
            corsi.add(courseRepository.findById(l).get());
        }

        Set set = new HashSet<>(corsi);

        List<Corso> corsoList= new ArrayList<>();
        corsoList.addAll(set);
        carrello.setCorso(corsoList);

        return carrelloRepository.save(carrello);
    }

    public Carrello updateById(long id,CarrelloDTO carrelloDTO) {
        Carrello carrello = carrelloRepository.findById(id).get();
        carrello.setUser(userRepository.findById(carrelloDTO.user_id()).get());
        List<Corso> corsi = new ArrayList<>();

        for (Long l : carrelloDTO.corso_id()) {
            corsi.add(courseRepository.findById(l).get());
        }

        Set set = new HashSet<>(corsi);

        List<Corso> corsoList = new ArrayList<>();
        corsoList.addAll(set);
        carrello.setCorso(corsoList);

        return carrelloRepository.save(carrello);
    }


    public Carrello svuota (long id){
        Carrello carrello= carrelloRepository.findById(id).get();

        carrello.getCorso().clear();
        return carrelloRepository.save(carrello);
    }

public Carrello getByUserId(long userId){
        return  carrelloRepository.findByUser_Id(userId).orElseThrow(() -> new BadRequestException("Non ci sono carrelli per l'user_id: " + userId));
}
}
