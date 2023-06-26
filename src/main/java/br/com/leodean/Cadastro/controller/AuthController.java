package br.com.leodean.Cadastro.controller;

import br.com.leodean.Cadastro.domain.Login;
import br.com.leodean.Cadastro.domain.databaseDomain.UserDataBase;
import br.com.leodean.Cadastro.service.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody Login user){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        Authentication authentication = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var userResponse = (UserDataBase) authentication.getPrincipal();

       var token =  tokenService.gerarToken(userResponse);
        return token;
    }
}
