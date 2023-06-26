package br.com.leodean.Cadastro.service.interfaces;

import br.com.leodean.Cadastro.domain.Address;
import br.com.leodean.Cadastro.domain.dto.AddressDTO;

import java.util.List;

public interface IAddressService {

    AddressDTO createAddress(Address request);

    AddressDTO getAddress(String addressId);

    List<AddressDTO> getAddressByCustomerId(String customerID);

    void deleteAddress(String addressId);
}
