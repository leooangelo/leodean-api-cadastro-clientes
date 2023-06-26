package br.com.leodean.Cadastro.service.interfaces;

import br.com.leodean.Cadastro.domain.Car;
import br.com.leodean.Cadastro.domain.dto.CarDTO;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;

public interface ICarService {
    CarDTO createCar(Car request);

    CarDTO getCar(String carId);

    void deleteCar(String carId);
}
