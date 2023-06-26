package br.com.leodean.Cadastro.controller;

import br.com.leodean.Cadastro.domain.Car;
import br.com.leodean.Cadastro.domain.data.EnvelopData;
import br.com.leodean.Cadastro.domain.dto.CarDTO;
import br.com.leodean.Cadastro.domain.dto.CustomerDTO;
import br.com.leodean.Cadastro.service.interfaces.ICarService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @CacheEvict(value = "car", allEntries = true)
    @PostMapping
    public EnvelopData<CarDTO> createCar(@RequestBody @Valid Car request) {
        return new EnvelopData<CarDTO>(carService.createCar(request));
    }

    @Cacheable(value = "car", key = "#carId")
    @GetMapping("/{car_id}")
    public EnvelopData<CarDTO> getCar(@PathVariable("car_id") String carId) {
        return new EnvelopData<CarDTO>(carService.getCar(carId));
    }

    @DeleteMapping("/{car_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "car", key = "#carId")
    private void deleteCar(@PathVariable("car_id") String carId){
        carService.deleteCar(carId);
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
