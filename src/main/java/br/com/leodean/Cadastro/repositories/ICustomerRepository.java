package br.com.leodean.Cadastro.repositories;


import br.com.leodean.Cadastro.domain.databaseDomain.AddressDataBase;
import br.com.leodean.Cadastro.domain.databaseDomain.CustomerDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ICustomerRepository extends JpaRepository<CustomerDataBase, String> {
    List<CustomerDataBase> findAllByOrderByNameAsc();

    @Query("select a from AddressDataBase a where a.customer.customerID like :customer")
    List<AddressDataBase> findByCustomerLike(@Param("customer") String customer);

    Optional<CustomerDataBase> findByEmail(String email);

    Optional<CustomerDataBase> findByCell(String cell);
}
