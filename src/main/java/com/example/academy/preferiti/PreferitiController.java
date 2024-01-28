package com.example.academy.preferiti;

import com.example.academy.exception.BadRequestException;
import com.example.academy.payloads.entities.PreferitiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preferiti")
public class PreferitiController {

    @Autowired
    PreferitiService preferitiService;


    @PostMapping("")
    public Preferiti save(@RequestBody @Validated PreferitiDTO preferitiDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return preferitiService.save(preferitiDTO);
    }

@DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable long id){
        return preferitiService.deleteById(id);
}

@PutMapping("/{id}")
    public Preferiti updateById(@PathVariable long id, @RequestBody PreferitiDTO preferitiDTO){
        return preferitiService.updateById(id,preferitiDTO);
}

@GetMapping("/user/{id}")
    public Preferiti getByUserId(@PathVariable long id){
        return preferitiService.getByUserId(id);
}

    @GetMapping("/{id}")
    public Preferiti getById(@PathVariable long id){
        return preferitiService.getById(id);
    }
}
