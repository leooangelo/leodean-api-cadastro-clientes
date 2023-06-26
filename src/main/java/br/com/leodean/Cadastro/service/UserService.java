package br.com.leodean.Cadastro.service;

import br.com.leodean.Cadastro.domain.Login;
import br.com.leodean.Cadastro.domain.databaseDomain.UserDataBase;
import br.com.leodean.Cadastro.exceptions.ExceptionApiCadastro;
import br.com.leodean.Cadastro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(Login login) {
        try {
            var userDataBase = mappToUserDataBase(login);
            userRepository.save(userDataBase);

        } catch (ExceptionApiCadastro e) {
            throw e;
        } catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "USER-01", e.getMessage());
        }
    }

    private UserDataBase mappToUserDataBase(Login login) {
        return UserDataBase.builder()
                .senha(new BCryptPasswordEncoder().encode(login.getPassword()))
                .email(login.getEmail())
                .build();
    }
}
