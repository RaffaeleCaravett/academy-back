package com.example.academy.carrello;

import com.example.academy.exception.BadRequestException;
import com.example.academy.payloads.entities.CarrelloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {
    @Autowired
    CarrelloService carrelloService;


    @PostMapping("")
    public Carrello save(@RequestBody @Validated CarrelloDTO carrelloDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
    return carrelloService.save(carrelloDTO);
    }

    @GetMapping("/user/{user_id}")
    public Carrello findByUserId(@PathVariable long user_id){
        return carrelloService.getByUserId(user_id);
    }

    @GetMapping("/{id}")
    public Carrello svuota(@PathVariable long id){
        return carrelloService.svuota(id);
    }

    @PutMapping("/{id}")
    public Carrello updateById(@PathVariable long id,@RequestBody CarrelloDTO carrelloDTO){
        return carrelloService.updateById(id,carrelloDTO);
    }
}
