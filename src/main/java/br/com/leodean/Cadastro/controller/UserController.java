package br.com.leodean.Cadastro.controller;

import br.com.leodean.Cadastro.domain.Login;
import br.com.leodean.Cadastro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private void createUser(@RequestBody @Valid Login login){

        userService.createUser(login);
    }

}
