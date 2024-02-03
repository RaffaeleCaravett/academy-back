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
public Page<Corso> findByParams(@RequestParam(defaultValue = "") String name, @RequestParam double price, @RequestParam(defaultValue = "") String descrizione, @RequestParam long docente,@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "9") int size,
    @RequestParam(defaultValue = "id") String orderBy){

    return courseService.findByParams(name,price,descrizione,docente,page,size,orderBy);
    }
    @GetMapping("/nome/{nome}")
    public Page<Corso> findByNome(@PathVariable String nome,@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "9") int size,
                                  @RequestParam(defaultValue = "id") String orderBy){

        return courseService.findByNome(nome,page,size,orderBy);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Corso updateById(@PathVariable long id,@RequestBody CorsoDTO corsoDTO){
    return courseService.findByIdAndUpdate(id,corsoDTO);
    }
}
