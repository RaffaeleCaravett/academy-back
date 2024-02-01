package com.example.academy.preferiti;

import com.example.academy.course.Corso;
import com.example.academy.course.CourseRepository;
import com.example.academy.exception.BadRequestException;
import com.example.academy.payloads.entities.PreferitiDTO;
import com.example.academy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreferitiService {
    @Autowired
    PreferitiRepository preferitiRepository;
@Autowired
    UserRepository userRepository;
@Autowired
    CourseRepository courseRepository;
    public Preferiti save(PreferitiDTO preferiti){
        Preferiti preferiti1= new Preferiti();

        preferiti1.setUser(userRepository.findById(preferiti.user_id()).get());
        List<Corso> corsos = new ArrayList<>();

        for(Long l : preferiti.corso_id()){
            corsos.add(courseRepository.findById(l).get());
        }
        preferiti1.setCorso(corsos);
        return preferitiRepository.save(preferiti1);
    }

    public Preferiti getByUserId(long userId){
        return preferitiRepository.findByUser_Id(userId);
    }
    public Preferiti getById(long id){
        return  preferitiRepository.findById(id).get();
    }

    public boolean deleteById(long id){
        try{
            preferitiRepository.deleteById(id);
return true;
        }catch (Exception e){
            return false;
        }
    }
public Preferiti updateById(long id, PreferitiDTO preferitiDTO){
        Preferiti preferiti1= preferitiRepository.findById(id).get();

    preferiti1.setUser(userRepository.findById(preferitiDTO.user_id()).get());
    List<Corso> corsos = new ArrayList<>();

    for(Long l : preferitiDTO.corso_id()){
        corsos.add(courseRepository.findById(l).get());
    }
    preferiti1.setCorso(corsos);
    return preferitiRepository.save(preferiti1);
}

public Preferiti svuotaById(long id){
        Preferiti preferiti= this.getById(id);
        preferiti.getCorso().clear();
        return preferitiRepository.save(preferiti);

}

}
