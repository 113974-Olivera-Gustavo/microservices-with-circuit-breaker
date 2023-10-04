package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.ExchangeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExchangeService {

     List<ExchangeDto> getExchanges();
}
