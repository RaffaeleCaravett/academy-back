package com.example.academy.auth;


import com.example.academy.exception.BadRequestException;
import com.example.academy.payloads.entities.Token;
import com.example.academy.payloads.entities.UserLoginDTO;
import com.example.academy.payloads.entities.UserLoginSuccessDTO;
import com.example.academy.payloads.entities.UserRegistrationDTO;
import com.example.academy.security.JWTTools;
import com.example.academy.user.User;
import com.example.academy.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService utenteService;



    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body) throws Exception {

        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public User saveUser(@RequestBody @Validated UserRegistrationDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerUser(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{token}")
    @ResponseStatus(HttpStatus.OK)
    public User verifyToken(@PathVariable String token){
            return jwtTools.verifyToken(token);
    }
    @GetMapping("/refreshToken/{refreshToken}")
    @ResponseStatus(HttpStatus.OK)
    public Token verifyRefreshToken(@PathVariable String refreshToken){
        return jwtTools.verifyRefreshToken(refreshToken);
    }


    @GetMapping("/user")
    public Page<User> getUser(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(defaultValue = "id") String orderBy){
        return authService.getUtenti(page, size, orderBy);
    }

}