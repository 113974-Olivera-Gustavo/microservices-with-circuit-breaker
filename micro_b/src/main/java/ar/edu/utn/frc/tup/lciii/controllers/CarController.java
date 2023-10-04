package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.CarDto;
import ar.edu.utn.frc.tup.lciii.models.Car;
import ar.edu.utn.frc.tup.lciii.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/Auto")
public class CarController {

    private final CarService carService;
    private final String exchangeServiceUrl = "http://localhost:8080/Exchange/getExchange";
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CarDto>> getCars(){
        return ResponseEntity.ok(carService.getCars());
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<CarDto> getCarById(Long id){
        return ResponseEntity.ok(carService.getCarById(id));
    }
}
