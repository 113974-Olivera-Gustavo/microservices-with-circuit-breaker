package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.common.ExchangeDto;
import ar.edu.utn.frc.tup.lciii.entities.ExchangeEntity;
import ar.edu.utn.frc.tup.lciii.repositories.ExchangeRepository;
import ar.edu.utn.frc.tup.lciii.services.ExchangeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private final ModelMapper modelMapper;
    private final ExchangeRepository exchangeRepository;

    @Autowired
    public ExchangeServiceImpl(ModelMapper modelMapper, ExchangeRepository exchangeRepository) {
        this.modelMapper = modelMapper;
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public List<ExchangeDto> getExchanges() {

        List<ExchangeEntity> exchangesEntity = exchangeRepository.findAll();
        List<ExchangeDto> exchangesDto = new ArrayList<>();

        for(ExchangeEntity exchangeEntity : exchangesEntity){
            ExchangeDto exchangeDto = new ExchangeDto();
            exchangeDto.setTo(exchangeEntity.getTo());
            exchangeDto.setExchange_rate(exchangeEntity.getExchange_rate());
            exchangeDto.setFrom(exchangeEntity.getFrom());
            exchangesDto.add(exchangeDto);
        }
        return exchangesDto;
    }
}
