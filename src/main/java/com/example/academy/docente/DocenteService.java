package com.example.academy.docente;

import com.example.academy.course.Corso;
import com.example.academy.exception.NotFoundException;
import com.example.academy.materia.Materia;
import com.example.academy.materia.MateriaRepository;
import com.example.academy.payloads.entities.CorsoDTO;
import com.example.academy.payloads.entities.DocenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocenteService {

    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    MateriaRepository materiaRepository;

    public Docente save(DocenteDTO docenteDTO){
        Docente docente = new Docente();

        docente.setNome(docenteDTO.nome());
        List<Materia> materiaList= new ArrayList<>();

        for(Long l : docenteDTO.materia_id()){
            materiaList.add(materiaRepository.findById(l).get());
        }
        docente.setMateria(materiaList);

        return docenteRepository.save(docente);

    }

    public Page<Docente> getAll(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return docenteRepository.findAll(pageable);
    }
    public List<Docente> getAll() {
        return docenteRepository.findAll();
    }

    public Docente findById(long id) throws NotFoundException {
        return docenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Docente findByIdAndUpdate(long id, DocenteDTO docenteDTO){
        Docente docente= docenteRepository.findById(id).get();

        docente.setNome(docenteDTO.nome());
        List<Materia> materiaList= new ArrayList<>();

        for(Long l : docenteDTO.materia_id()){
            materiaList.add(materiaRepository.findById(l).get());
        }
        docente.setMateria(materiaList);

        return docenteRepository.save(docente);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Docente found = this.findById(id);
        docenteRepository.delete(found);
    }
}
