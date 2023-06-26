package br.com.leodean.Cadastro.domain.mapper;

import br.com.leodean.Cadastro.domain.Car;
import br.com.leodean.Cadastro.domain.databaseDomain.CarDataBase;
import br.com.leodean.Cadastro.domain.databaseDomain.CustomerDataBase;
import br.com.leodean.Cadastro.domain.dto.CarDTO;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CarMapper {


    public static CarDTO mappToResponse(CarDataBase carDataBase) {
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

    public static CarDataBase mapperToDataBase(Car request, CustomerDataBase customer) {
        return CarDataBase.builder()
                .carID(UUID.randomUUID().toString())
                .constructor(request.getConstructor())
                .name(request.getName())
                .model(request.getModel())
                .customer(customer)
                .build();
    }
}
