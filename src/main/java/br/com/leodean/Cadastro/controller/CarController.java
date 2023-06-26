package br.com.leodean.Cadastro.controller;

import br.com.leodean.Cadastro.domain.Car;
import br.com.leodean.Cadastro.domain.data.EnvelopData;
import br.com.leodean.Cadastro.domain.dto.CarDTO;
import br.com.leodean.Cadastro.service.interfaces.ICarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private final ICarService carService;

    public CarController(ICarService carService) {
        this.carService = carService;
    }


    /**
     * @param request
     * @return
     */
    @PostMapping
    public EnvelopData<CarDTO> createCar(@RequestBody @Valid Car request) {
        return new EnvelopData<CarDTO>(carService.createCar(request));
    }

//    /**
//     *
//     * @param carID
//     * @return
//     */
//    @GetMapping("/{car_id}")
//    public EnvelopData<CarDTO> getCar (@PathVariable("car_id") String carID){
//        return new EnvelopData<CarDTO>(carService.getCar(carID));
//    }
}
