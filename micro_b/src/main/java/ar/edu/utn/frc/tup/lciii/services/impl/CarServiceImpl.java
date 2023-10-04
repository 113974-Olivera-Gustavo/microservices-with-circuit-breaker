package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.common.CarDto;
import ar.edu.utn.frc.tup.lciii.dtos.common.ExchangeDto;
import ar.edu.utn.frc.tup.lciii.entities.CarEntity;
import ar.edu.utn.frc.tup.lciii.models.Car;
import ar.edu.utn.frc.tup.lciii.repositories.CarRepository;
import ar.edu.utn.frc.tup.lciii.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final String exchangeServiceUrl ="http://localhost:8080/Exchange/getExchange";
    private final RestTemplate restTemplate = new RestTemplate();

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public List<CarDto> getCars() {
        List<CarEntity> carsEntity = carRepository.findAll();
        List<CarDto> carDtos = new ArrayList<>();

        // Obtener la tasa de cambio del microservicio A
        ExchangeDto[] exchangeDtos = restTemplate.getForObject(exchangeServiceUrl, ExchangeDto[].class);
        BigDecimal exchangeRate = null;
        for (ExchangeDto exchangeDto : exchangeDtos) {
            if (exchangeDto.getFrom().equals("USD") && exchangeDto.getTo().equals("ARS")) {
                exchangeRate = exchangeDto.getExchange_rate();
                break;
            }
        }
        for (CarEntity carEntity : carsEntity) {
            CarDto carDto = modelMapper.map(carEntity, CarDto.class);

            // Aplicar la conversión de moneda si la moneda es USD
            if (carEntity.getCurrency().equals("USD")) {
                BigDecimal convertedPrice = carEntity.getPrice().multiply(exchangeRate);
                carDto.setPrice(convertedPrice);
            }
            carDtos.add(carDto);
        }
        return carDtos;
    }


    @Override
    public CarDto getCarById(Long id) {
        Optional<CarEntity> optionalCarEntity = carRepository.findById(id);
        if(optionalCarEntity.isPresent()){
            CarEntity carEntity = optionalCarEntity.get();
            //maper entidad a un DTO
            CarDto carDto = modelMapper.map(carEntity, CarDto.class);
            return carDto;
        }
        else {
            throw new IllegalArgumentException("No existe el auto");
        }
    }


}
