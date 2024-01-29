package com.example.academy.materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    MateriaRepository materiaRepository;


    public Materia save(Materia materia){
return materiaRepository.save(materia);
    }

    public List<Materia> getAll(){
        return materiaRepository.findAll();
    }

    public Materia updateById(long id,Materia materia){
        Materia materia1= materiaRepository.findById(id).get();
        materia1.setNome(materia.getNome());
        return materiaRepository.save(materia);
    }

    public boolean deleteById(long id){
        try{
            materiaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Materia getById(long id){
        return materiaRepository.findById(id).get();
    }
}