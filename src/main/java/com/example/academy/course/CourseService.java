package com.example.academy.course;

import com.example.academy.docente.Docente;
import com.example.academy.docente.DocenteRepository;
import com.example.academy.exception.BadRequestException;
import com.example.academy.exception.NotFoundException;
import com.example.academy.materia.Materia;
import com.example.academy.materia.MateriaRepository;
import com.example.academy.payloads.entities.CorsoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    DocenteRepository docenteRepository;
@Autowired
    MateriaRepository materiaRepository;
    public Corso save(CorsoDTO corsoDTO){
        Corso corso = new Corso();

        corso.setNome(corsoDTO.nome());
        corso.setPrezzo(corsoDTO.prezzo());
        corso.setDescrizione(corsoDTO.descrizione());
        List<Docente> docenteList= new ArrayList<>();
List<Materia> materiaList = new ArrayList<>();

        for(Long l : corsoDTO.docente_id()){
            docenteList.add(docenteRepository.findById(l).get());
        }

        for(Long l : corsoDTO.materia_id()){
            materiaList.add(materiaRepository.findById(l).get());
        }
        corso.setMateria(materiaList);
        corso.setDocente(docenteList);

    return courseRepository.save(corso);

    }

    public Page<Corso> getAll(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return courseRepository.findAll(pageable);
    }

    public Corso findById(long id) throws NotFoundException {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Corso findByIdAndUpdate(long id, CorsoDTO corsoDTO){
Corso corso= courseRepository.findById(id).get();

        corso.setNome(corsoDTO.nome());
        corso.setPrezzo(corsoDTO.prezzo());
        corso.setDescrizione(corsoDTO.descrizione());
        List<Docente> docenteList= new ArrayList<>();
        List<Materia> materiaList = new ArrayList<>();

        for(Long l : corsoDTO.docente_id()){
            docenteList.add(docenteRepository.findById(l).get());
        }
        corso.setDocente(docenteList);
        for(Long l : corsoDTO.materia_id()){
            materiaList.add(materiaRepository.findById(l).get());
        }
        corso.setMateria(materiaList);
        return courseRepository.save(corso);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Corso found = this.findById(id);
        courseRepository.delete(found);
    }
public Page<Corso> findByParams(String nome,double prezzo,String descrizione,long docente_id,int page,int size,String orderBy){
    Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
    if(docente_id!=0&&prezzo==0){
        return courseRepository.findByNomeContainingIgnoreCaseAndDescrizioneContainingIgnoreCaseAndDocente_IdEquals(nome,descrizione,docente_id,pageable);
    }else if(docente_id==0&&prezzo==0){
        return courseRepository.findByNomeContainingIgnoreCaseAndDescrizioneContainingIgnoreCase(nome,descrizione,pageable);
    }else if(docente_id==0&&prezzo!=0){
        return courseRepository.findByNomeContainingIgnoreCaseAndDescrizioneContainingIgnoreCaseAndPrezzoEquals(nome,descrizione,prezzo,pageable);
    }else {
        return courseRepository.findByNomeContainingIgnoreCaseAndPrezzoEqualsAndDescrizioneContainingIgnoreCaseAndDocente_IdEquals(nome,prezzo,descrizione,docente_id,pageable);    }
}
public Page<Corso> findByNome(String nome,int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page ,size, Sort.by(orderBy));
        return courseRepository.findAllByNomeContainingIgnoreCase(nome,pageable);
}
}
