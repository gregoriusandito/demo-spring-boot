package com.example.demo.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@SuperBuilder
public class SaldoResponse {

    private long accountNumber;

    private long customerNumber;

    private BigDecimal balance;

}
