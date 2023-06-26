package br.com.leodean.Cadastro.repositories;

import br.com.leodean.Cadastro.domain.databaseDomain.UserDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDataBase, Long> {
    UserDetails findByEmail(String email);
}
