package com.example.academy.docente;

import com.example.academy.course.Corso;
import com.example.academy.exception.BadRequestException;
import com.example.academy.payloads.entities.CorsoDTO;
import com.example.academy.payloads.entities.DocenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/docente")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Docente save(@RequestBody @Validated DocenteDTO docenteDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return docenteService.save(docenteDTO);
    }
    @GetMapping("")
    public Page<Docente> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "9") int size,
                              @RequestParam(defaultValue = "id") String orderBy) {
        return docenteService.getAll(page, size, orderBy);
    }
    @GetMapping("/{id}")
    public Docente findById(@PathVariable long id){
        return docenteService.findById(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteById(@PathVariable long id){
        try {
            docenteService.findByIdAndDelete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Docente updateById(@PathVariable long id,@RequestBody DocenteDTO docenteDTO){
        return docenteService.findByIdAndUpdate(id,docenteDTO);
    }
}
