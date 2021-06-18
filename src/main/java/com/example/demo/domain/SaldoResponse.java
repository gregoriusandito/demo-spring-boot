package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@SuperBuilder
public class SaldoResponse {
    private Long accountNumber;
    private String customerName;
    private BigDecimal balance;
    @JsonIgnore
    private Long customerNumber;
}
