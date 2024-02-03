package com.example.academy.secret;

import com.example.academy.enums.Role;
import com.example.academy.exception.BadRequestException;
import com.example.academy.user.User;
import com.example.academy.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secret")
public class SecretController {

@Autowired
    SecretService secretService;
@Autowired
    UserService userService;
@PostMapping("")
    public Secret save(@RequestBody @Validated Secret secret, BindingResult validation){
    if(validation.hasErrors()){
        throw new BadRequestException(validation.getAllErrors());
    }
    List<Secret> secretList = this.getAll();
    if(secretList.isEmpty()){
        return secretService.save(secret);
    }else{
        return this.updateById(secretList.get(0).getId(),secret);
    }
}

@GetMapping("")
    public List<Secret> getAll(){
    return secretService.getAll();
}

@PutMapping("/{id}")
    public Secret updateById(@PathVariable long id, @RequestBody Secret secret){
    return secretService.updateById(id,secret);
}

@DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable long id){
    return secretService.deleteById(id);
}

@PostMapping("/verify/{id}")
public boolean verifySecret(@PathVariable long id,@RequestBody Secret secret) {

    User user = userService.findById(id);
    if (user.getRole().equals(Role.USER)) {
        throw new BadRequestException("Solo gli admin hanno accesso alla verifica");
    }

    Secret secret1 = this.getAll().get(0);
    if (secret1.getCodice().equals(secret.getCodice())) {
        return true;
    } else {
        return false;
    }
}
}
