package br.com.leodean.Cadastro.service;

import br.com.leodean.Cadastro.domain.Address;
import br.com.leodean.Cadastro.domain.Customer;
import br.com.leodean.Cadastro.domain.databaseDomain.AddressDataBase;
import br.com.leodean.Cadastro.domain.databaseDomain.CustomerDataBase;
import br.com.leodean.Cadastro.domain.dto.AddressDTO;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;
import br.com.leodean.Cadastro.exceptions.ExceptionApiCadastro;
import br.com.leodean.Cadastro.integration.ViaCepService;
import br.com.leodean.Cadastro.integration.interfaces.IViaCepService;
import br.com.leodean.Cadastro.repositories.AddressRepository;
import br.com.leodean.Cadastro.repositories.ICustomerRepository;
import br.com.leodean.Cadastro.service.interfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe de serviço que manipula toda informação de Address.
 */
@Service
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;
    private final ICustomerRepository _iCustomerRepository;

    private IViaCepService viaCepService;

    public AddressService(AddressRepository addressRepository, ICustomerRepository _iCustomerRepository,IViaCepService viaCepService) {
        this.addressRepository = addressRepository;
        this._iCustomerRepository = _iCustomerRepository;
        this.viaCepService = viaCepService;
    }

    /**
     * Metodo de cria um endereço e salva na base de dados
     *
     * @param request
     * @return address
     */
    @Override
    public AddressDTO createAddress(Address request) {
        try {

            var cepCorreio = viaCepService.getCep(request.getZipCode());

            var customer = _iCustomerRepository.findById(request.getCustomer().getCustomerID()).orElseThrow(() -> new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CUSTOMER-03"));

            var address = mapperToDataBase(request, customer);

            addressRepository.save(address);

            return mappToResponse(address);
        } catch (ExceptionApiCadastro e) {
            throw e;
        } catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "ADDRESS-01", e.getMessage());
        }
    }


    /**
     * Metodo que retorna um endereço especifico pelo ID.
     *
     * @param addressId
     * @return
     */
    @Override
    public AddressDTO getAddress(String addressId) {
        var address = addressRepository.findById(addressId).get();
        return AddressDTO.builder()
                .adress_id(address.getAddressID())
                .state(address.getState())
                .city(address.getCity())
                .streetName(address.getStreetName())
                .number(address.getNumber())
                .zipCode(address.getZipCode())
                .complement(address.getComplement())
                .customer(CustomerDTO.builder()
                        .customerID(address.getCustomer().getCustomerID())
                        .build())
                .build();
    }

    @Override
    public List<AddressDTO> getAddressByCustomerId(String customerID) {
        var responseDataBase = addressRepository.findByCustomerLike(customerID);
        return mappToListAddressDTO(responseDataBase);
    }

    @Override
    public void deleteAddress(String addressId) {
        addressRepository.deleteById(addressId);
    }

    private AddressDataBase mapperToDataBase(Address request, CustomerDataBase customerDataBase) {
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
//    private AddressDataBase mapperToDataBase(Address request){
//        return AddressDataBase.builder()
//                .addressID(UUID.randomUUID().toString())
//                .state(request.getState())
//                .city(request.getCity())
//                .streetName(request.getStreetName())
//                .number(request.getNumber())
//                .zipCode(request.getZipCode())
//                .complement(request.getComplement())
//                .customer(CustomerMapper.mappToDataBase(request.getCustomer()))
//                .build();
//    }

    /**
     * Mapper que retorna o objeto customer somente com o id do customer preenchido.
     *
     * @param
     * @return
     */
    private CustomerDataBase mappToDataBase(Customer request) {
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
    private CustomerDTO mappToCustomerDTO(AddressDataBase customer) {
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
    private List<AddressDTO> mappToListAddressDTO(List<AddressDataBase> list) {
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

    private AddressDTO mappToResponse(AddressDataBase addressDataBase) {
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
