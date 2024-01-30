package com.example.academy.course;

import com.example.academy.exception.BadRequestException;
import com.example.academy.payloads.entities.CorsoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corso")
public class CourseController {

    @Autowired
    CourseService courseService;

@PostMapping("")
@PreAuthorize("hasAuthority('ADMIN')")
    public Corso save(@RequestBody @Validated CorsoDTO corsoDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
    return courseService.save(corsoDTO);
    }
@GetMapping("")
    public Page<Corso> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "9") int size,
                              @RequestParam(defaultValue = "id") String orderBy) {
        return courseService.getAll(page, size, orderBy);
    }
    @GetMapping("/{id}")
    public Corso findById(@PathVariable long id){
        return courseService.findById(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteById(@PathVariable long id){
    try {
        courseService.findByIdAndDelete(id);
    return true;
    }catch (Exception e){
        return false;
    }
    }
@GetMapping("/params")
public List<Corso> findByParams(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "0") double price, @RequestParam(defaultValue = "") String descrizione, @RequestParam(defaultValue = "0"
) long docente_id,@RequestParam(defaultValue = "") long materia_id){
    return courseService.findByParams(name,price,descrizione,docente_id,materia_id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Corso updateById(@PathVariable long id,@RequestBody CorsoDTO corsoDTO){
    return courseService.findByIdAndUpdate(id,corsoDTO);
    }
}
