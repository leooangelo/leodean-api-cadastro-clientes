package br.com.leodean.Cadastro.domain.mapper;

import br.com.leodean.Cadastro.domain.Address;
import br.com.leodean.Cadastro.domain.Customer;
import br.com.leodean.Cadastro.domain.databaseDomain.AddressDataBase;
import br.com.leodean.Cadastro.domain.databaseDomain.CustomerDataBase;
import br.com.leodean.Cadastro.domain.dto.AddressDTO;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AddressMapper {

    public static AddressDataBase mapperToDataBase(Address request, CustomerDataBase customerDataBase) {
        return AddressDataBase.builder()
                .addressID(UUID.randomUUID().toString())
                .state(request.getState())
                .city(request.getCity())
                .streetName(request.getStreetName())
                .number(request.getNumber())
                .zipCode(request.getZipCode())
                .complement(request.getComplement())
                .customer(customerDataBase)
                .build();
    }

    /**
     * Mapper que retorna o objeto customer somente com o id do customer preenchido.
     *
     * @param
     * @return
     */
    public static CustomerDataBase mappToDataBase(Customer request) {
        if (request == null)
            return new CustomerDataBase();
        return CustomerDataBase.builder()
                .customerID(request.getCustomerID())
                .name(request.getName())
                .email(request.getEmail())
                .cell(request.getCell())
                .build();
    }

    /**
     * Mapper que retorna o objeto customer somente com o id do customer preenchido.
     *
     * @param customer
     * @return
     */
    public static CustomerDTO mappToCustomerDTO(AddressDataBase customer) {
        return CustomerDTO.builder()
                //.customerID(customer.getCustomer())
                .build();
    }

    /**
     * Mapper que retorna o objeto customer somente com o id do customer preenchido.
     *
     * @param list
     * @return
     */
    public static List<AddressDTO> mappToListAddressDTO(List<AddressDataBase> list) {
        List<AddressDTO> addressDTOS = new ArrayList<>();
        for (AddressDataBase obj : list) {
            addressDTOS.add(AddressDTO.builder()
                    .adress_id(obj.getAddressID())
                    .state(obj.getState())
                    .city(obj.getCity())
                    .streetName(obj.getStreetName())
                    .number(obj.getNumber())
                    .zipCode(obj.getZipCode())
                    .complement(obj.getComplement())
                    .build());
        }
        return addressDTOS;
    }

    public static AddressDTO mappToResponse(AddressDataBase addressDataBase) {
        return AddressDTO.builder()
                .adress_id(addressDataBase.getAddressID())
                .state(addressDataBase.getState())
                .city(addressDataBase.getCity())
                .streetName(addressDataBase.getStreetName())
                .number(addressDataBase.getNumber())
                .zipCode(addressDataBase.getZipCode())
                .complement(addressDataBase.getComplement())
                .customer(CustomerDTO.builder()
                        .customerID(addressDataBase.getCustomer().getCustomerID())
                        .build())
                .build();
    }
}
