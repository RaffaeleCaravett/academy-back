package com.example.academy.materia;

import com.example.academy.docente.Docente;
import com.example.academy.docente.DocenteService;
import com.example.academy.exception.BadRequestException;
import com.example.academy.payloads.entities.DocenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @PostMapping("")
    public Materia save(@RequestBody @Validated Materia materia, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return materiaService.save(materia);
    }
    @GetMapping("")
    public Page<Materia> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "9") int size,
                                @RequestParam(defaultValue = "id") String orderBy) {
        return materiaService.getAll(page, size, orderBy);
    }
    @GetMapping("/{id}")
    public Materia findById(@PathVariable long id){
        return materiaService.getById(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteById(@PathVariable long id){
        try {
            materiaService.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Materia updateById(@PathVariable long id,@RequestBody Materia materia){
        return materiaService.updateById(id,materia);
    }
}
