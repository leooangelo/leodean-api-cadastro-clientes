package br.com.leodean.Cadastro.domain.mapper;

import br.com.leodean.Cadastro.domain.Address;
import br.com.leodean.Cadastro.domain.Car;
import br.com.leodean.Cadastro.domain.Customer;
import br.com.leodean.Cadastro.domain.databaseDomain.CustomerDataBase;
import br.com.leodean.Cadastro.domain.dto.AddressDTO;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {


    public static List<CustomerDTO> mappToResponse(List<CustomerDataBase> customerDatabase) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (CustomerDataBase customerDataBase : customerDatabase) {
            customerDTOList.add(CustomerDTO.builder().customerID(customerDataBase.getCustomerID())
                    .name(customerDataBase.getName())
                    .cell(customerDataBase.getCell())
                    .email(customerDataBase.getEmail()).build());
        }
        return customerDTOList;
    }

    public static CustomerDTO mappToResponse(CustomerDataBase customerDataBase, List<AddressDTO> address) {
        return CustomerDTO.builder()
                .customerID(customerDataBase.getCustomerID())
                .name(customerDataBase.getName())
                .email(customerDataBase.getEmail())
                .cell(customerDataBase.getCell())
                .addressList(address.stream().map(x -> x.getAdress_id()).collect(Collectors.toList()))
                .build();
    }

    public static void customerIDRequestCreated(Customer customer, String customerIDCreated) {
        customer.setCustomerID(customerIDCreated);
    }


    public static Address mappToAddressRequest(Address address, Customer customer) {
        return Address.builder()
                .state(address.getState())
                .city(address.getCity())
                .number(address.getNumber())
                .zipCode(address.getZipCode())
                .complement(address.getComplement())
                .streetName(address.getStreetName())
                .customer(customer)
                .build();
    }

    public static Car mappToCarRequest(Car car, Customer customer) {
        return Car.builder()
                .constructor(car.getConstructor())
                .name(car.getName())
                .model(car.getModel())
                .customer(customer)
                .build();
    }

    public static CustomerDataBase mappToDataBase(Customer request) {
        return CustomerDataBase.builder()
                .customerID(UUID.randomUUID().toString())
                .name(request.getName())
                .email(request.getEmail())
                .cell(request.getCell())
                .build();
    }

    public static CustomerDTO mappToResponse(CustomerDataBase customerDataBase) {
        return CustomerDTO.builder()
                .customerID(customerDataBase.getCustomerID())
                .name(customerDataBase.getName())
                .email(customerDataBase.getEmail())
                .cell(customerDataBase.getCell())
                .build();
    }


}
