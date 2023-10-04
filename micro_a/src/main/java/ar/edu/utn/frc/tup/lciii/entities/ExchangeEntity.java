package ar.edu.utn.frc.tup.lciii.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "Exchange")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"from\"")
    private String from;

    @Column(name = "\"to\"")
    private String to;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    public String getTo() {
        return to;
    }

    public BigDecimal getExchange_rate() {
        return this.exchangeRate;
    }

    public String getFrom() {
        return from;
    }
}
