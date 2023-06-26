package br.com.leodean.Cadastro.service.interfaces;

import br.com.leodean.Cadastro.domain.Customer;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    CustomerDTO createCustomer(Customer request);

    CustomerDTO getCustomer(String customerId);

    void deleteCustomer(String customerId);

    List<CustomerDTO> getAllCustomers();
}
