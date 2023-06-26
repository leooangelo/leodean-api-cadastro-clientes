package br.com.leodean.Cadastro.service;

import br.com.leodean.Cadastro.Utils.RandomGeneratorUtil;
import br.com.leodean.Cadastro.Utils.ValidatorUtils;
import br.com.leodean.Cadastro.domain.Address;
import br.com.leodean.Cadastro.domain.Car;
import br.com.leodean.Cadastro.domain.Customer;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;
import br.com.leodean.Cadastro.domain.mapper.CustomerMapper;
import br.com.leodean.Cadastro.exceptions.ExceptionApiCadastro;
import br.com.leodean.Cadastro.repositories.ICustomerRepository;
import br.com.leodean.Cadastro.service.interfaces.IAddressService;
import br.com.leodean.Cadastro.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Classe de serviço que manipula toda informação de customer
 */
@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private CarService carService;

    @Autowired
    private RandomGeneratorUtil randomGeneratorUtil;

    @Autowired
    private ValidatorUtils validatorUtils;

    /**
     * Metodo que cria um usuário e salva no banco de dados;
     *
     * @param request
     * @return buildCustomer
     */
    @Override
    public CustomerDTO createCustomer(Customer request) {
        try {
            customerExist(request.getEmail(), request.getCell());

            var customerDataBase = CustomerMapper.mappToDataBase(request);

            customerRepository.save(customerDataBase);

            var addressList = validatorUtils.isNullOrEmpty(request.getAddressList());
            if (!addressList) {
                for (Address address : request.getAddressList()) {
                    CustomerMapper.customerIDRequestCreated(request, customerDataBase.getCustomerID());
                    var addressRequest = CustomerMapper.mappToAddressRequest(address, request);
                    addressService.createAddress(addressRequest);
                }
            }

            var carList = validatorUtils.isNullOrEmpty(request.getCarsList());
            if (!carList) {
                for (Car car : request.getCarsList()) {
                    CustomerMapper.customerIDRequestCreated(request, customerDataBase.getCustomerID());
                    var carRequest = CustomerMapper.mappToCarRequest(car, request);
                    carService.createCar(carRequest);

                }
            }

            return CustomerMapper.mappToResponse(customerDataBase);
        } catch (ExceptionApiCadastro e) {
            throw e;
        } catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CUSTOMER-01", e.getMessage());
        }
    }

    /**
     * Pesquisar usuário por id;
     *
     * @param customerId
     * @return
     */
    @Override
    public CustomerDTO getCustomer(String customerId) {
        try {
            var customerDataBase = customerRepository.findById(customerId).get();
            var address = addressService.getAddressByCustomerId(customerId);
            return CustomerMapper.mappToResponse(customerDataBase, address);

        } catch (ExceptionApiCadastro | NoSuchElementException ex) {
            throw new ExceptionApiCadastro(HttpStatus.NOT_FOUND, "CUSTOMER-03");

        } catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CUSTOMER-02", e.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        var customerDatabase = customerRepository.findAllByOrderByNameAsc();

        return CustomerMapper.mappToResponse(customerDatabase);
    }

    @Override
    public void deleteCustomer(String customerId) {
        try {
            customerRepository.deleteById(customerId);
        } catch (ExceptionApiCadastro | EmptyResultDataAccessException ex) {
            throw new ExceptionApiCadastro(HttpStatus.NOT_FOUND, "CUSTOMER-05");

        } catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CUSTOMER-04", e.getMessage());
        }
    }

    private void customerExist(String email, String cell) {

        customerRepository.findByEmail(email)
                .ifPresent(check -> {
                    throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CUSTOMER-06");
                });

        customerRepository.findByCell(cell)
                .ifPresent(check -> {
                    throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CUSTOMER-07");
                });
    }
}
