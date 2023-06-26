package br.com.leodean.Cadastro.repositories;

import br.com.leodean.Cadastro.domain.databaseDomain.AddressDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressDataBase, String> {

    @Query("select a from AddressDataBase a where a.customer.customerID like :customer")
    List<AddressDataBase> findByCustomerLike(@Param("customer") String customer);
}
