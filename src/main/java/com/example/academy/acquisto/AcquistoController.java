package com.example.academy.acquisto;

import com.example.academy.exception.BadRequestException;
import com.example.academy.payloads.entities.AcquistoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acquisti")
public class AcquistoController {
    @Autowired
    AcquistoService acquistoService;

    @PostMapping("")
    public Acquisto save(@RequestBody @Validated AcquistoDTO acquistoDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
       return acquistoService.save(acquistoDTO);
    }

    @GetMapping("/carrello/{carrelloId}")
    public List<Acquisto> getByCarrelloId(@PathVariable long carrelloId){
        return acquistoService.getAllByCarrello_id(carrelloId);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Acquisto> getAll(){
        return acquistoService.getAll();
    }
}
