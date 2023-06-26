package br.com.leodean.Cadastro.service.interfaces;

import br.com.leodean.Cadastro.domain.Car;
import br.com.leodean.Cadastro.domain.dto.CarDTO;

public interface ICarService {
    CarDTO createCar(Car request);
}
