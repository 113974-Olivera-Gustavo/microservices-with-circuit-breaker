package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.CarDto;
import ar.edu.utn.frc.tup.lciii.models.Car;
import ar.edu.utn.frc.tup.lciii.services.impl.CarServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    List<CarDto> getCars();
    CarDto getCarById(Long id);
}
