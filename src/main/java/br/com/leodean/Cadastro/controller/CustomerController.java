package br.com.leodean.Cadastro.controller;

import br.com.leodean.Cadastro.domain.Customer;
import br.com.leodean.Cadastro.domain.data.EnvelopData;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;
import br.com.leodean.Cadastro.service.interfaces.ICustomerService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Leonardo Angelo
 * @since 25/01/2023
 * *
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final ICustomerService _customerService;

    public CustomerController(ICustomerService _customerService) {
        this._customerService = _customerService;
    }

        @CacheEvict(value = "customer", allEntries = true)
    @PostMapping
    public EnvelopData<CustomerDTO> createCustomer(@RequestBody @Valid Customer request) {
        return new EnvelopData<CustomerDTO>(_customerService.createCustomer(request));
    }

    @Cacheable(value = "customer", key = "#customerId")
    @GetMapping("/{customer_id}")
    public EnvelopData<CustomerDTO> getCustomer(@PathVariable("customer_id") String customerId) {
        return new EnvelopData<CustomerDTO>(_customerService.getCustomer(customerId));
    }

    @Cacheable(value = "customer")
    @GetMapping
    public EnvelopData<List<CustomerDTO>> getAllCustomers() {
        return new EnvelopData<List<CustomerDTO>>(_customerService.getAllCustomers());

    }

    @DeleteMapping("/{customer_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "customer", key = "#customerId")
    public void deleteCustomer(@PathVariable("customer_id") String customerId) {
        _customerService.deleteCustomer(customerId);
    }


    //    @CachePut(value = "customer", key = "#customerId")
    //    @PutMapping

    //TODO
//- expertos Tech
//- HATEOAS Michelli Brito
}
