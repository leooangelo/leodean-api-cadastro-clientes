package br.com.leodean.Cadastro.service;

import br.com.leodean.Cadastro.domain.Car;
import br.com.leodean.Cadastro.domain.dto.CarDTO;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;
import br.com.leodean.Cadastro.domain.mapper.CarMapper;
import br.com.leodean.Cadastro.exceptions.ExceptionApiCadastro;
import br.com.leodean.Cadastro.repositories.CarRepository;
import br.com.leodean.Cadastro.repositories.ICustomerRepository;
import br.com.leodean.Cadastro.service.interfaces.ICarService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

            var carDataBase = CarMapper.mapperToDataBase(request, customer);
            carRepository.save(carDataBase);

            return CarMapper.mappToResponse(carDataBase);
        } catch (ExceptionApiCadastro e) {
            throw e;
        } catch (Exception e) {
            throw new ExceptionApiCadastro(HttpStatus.INTERNAL_SERVER_ERROR, "CAR-01", e.getMessage());
        }
    }

    @Override
    public CarDTO getCar(String carId) {
        var car = carRepository.findById(carId).get();
        return CarDTO.builder()
                .carID(car.getCarID())
                .name(car.getName())
                .model(car.getModel())
                .constructor(car.getConstructor())
                .customer(CustomerDTO.builder().customerID(car.getCustomer().getCustomerID()).build())
                .build();
    }

    @Override
    public void deleteCar(String carId) {
        carRepository.deleteById(carId);
    }

}
