package br.com.leodean.Cadastro.service;

import br.com.leodean.Cadastro.domain.Car;
import br.com.leodean.Cadastro.domain.databaseDomain.CarDataBase;
import br.com.leodean.Cadastro.domain.databaseDomain.CustomerDataBase;
import br.com.leodean.Cadastro.domain.dto.CarDTO;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;
import br.com.leodean.Cadastro.exceptions.ExceptionApiCadastro;
import br.com.leodean.Cadastro.repositories.CarRepository;
import br.com.leodean.Cadastro.repositories.ICustomerRepository;
import br.com.leodean.Cadastro.service.interfaces.ICarService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Classe de serviço que manipula toda informação de Carro.
 */
@Service
public class CarService implements ICarService {

    private final CarRepository carRepository;

    private final ICustomerRepository _customerRepository;


    public CarService(CarRepository carRepository, ICustomerRepository _customerRepository) {
        this.carRepository = carRepository;
        this._customerRepository = _customerRepository;
    }

    @Override
    public CarDTO createCar(Car request) {

        try {
            var customer = _customerRepository.findById(request.getCustomer().getCustomerID()).orElseThrow(() -> new ExceptionApiCadastro(HttpStatus.BAD_REQUEST, "CUSTOMER-03"));

            var carDataBase = mapperToDataBase(request, customer);
            carRepository.save(carDataBase);

            return mappToResponse(carDataBase);
        } catch (ExceptionApiCadastro e) {
            throw e;
        } catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CAR-01", e.getMessage());
        }
    }

    private CarDTO mappToResponse(CarDataBase carDataBase) {
        return CarDTO.builder()
                .carID(carDataBase.getCarID())
                .constructor(carDataBase.getConstructor())
                .name(carDataBase.getName())
                .model(carDataBase.getModel())
                .customer(CustomerDTO.builder()
                        .customerID(carDataBase.getCustomer().getCustomerID())
                        .build())
                .build();
    }

    private CarDataBase mapperToDataBase(Car request, CustomerDataBase customer) {
        return CarDataBase.builder()
                .carID(UUID.randomUUID().toString())
                .constructor(request.getConstructor())
                .name(request.getName())
                .model(request.getModel())
                .customer(customer)
                .build();
    }

//    public CarDTO getCar(String carID) {
//
//        var car =carRepository.findById(carID).get();
//        return CarDTO.builder()
//                .carID(car.getCarID())
//                .constructor(car.getConstructor())
//                .name(car.getName())
//                .model(car.getModel())
//                .customer(mappToCustomerDTO(car.getCustomer()))
//                .build();
//
//    }
//
//
}
