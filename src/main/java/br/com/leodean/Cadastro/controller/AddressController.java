package br.com.leodean.Cadastro.controller;


import br.com.leodean.Cadastro.domain.Address;
import br.com.leodean.Cadastro.domain.data.EnvelopData;
import br.com.leodean.Cadastro.domain.dto.AddressDTO;
import br.com.leodean.Cadastro.service.interfaces.IAddressService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final IAddressService addressService;

    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }


    @PostMapping
    @CacheEvict(value = "address", allEntries = true)
    private EnvelopData<AddressDTO> createAddress(@RequestBody @Valid Address request) {
        return new EnvelopData<AddressDTO>(addressService.createAddress(request));
    }

    @GetMapping("/{address_id}")
    @Cacheable(value = "address", key = "#addressId")
    private EnvelopData<AddressDTO> getAddress(@PathVariable("address_id") String addressId) {
        return new EnvelopData<AddressDTO>(addressService.getAddress(addressId));
    }

    @DeleteMapping("/{address_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "address", key = "#addressId")
    private void deleteAddress(@PathVariable("address_id") String addressId) {
        addressService.deleteAddress(addressId);
    }
}
