package br.com.leodean.Cadastro.repositories;

import br.com.leodean.Cadastro.domain.databaseDomain.CarDataBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarDataBase, String> {
}
