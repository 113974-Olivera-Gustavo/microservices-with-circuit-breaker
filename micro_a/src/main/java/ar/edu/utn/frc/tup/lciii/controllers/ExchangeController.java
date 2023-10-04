package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.ExchangeDto;
import ar.edu.utn.frc.tup.lciii.services.ExchangeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;
    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/getExchange")
    public ResponseEntity<List<ExchangeDto>> getExchange(){
        return ResponseEntity.ok(exchangeService.getExchanges());
    }
}
