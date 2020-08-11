package com.example.demo.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@SuperBuilder
public class CheckSaldoResponse {

    private long accountNumber;

    private long customerNumber;

    private BigDecimal balance;

}
