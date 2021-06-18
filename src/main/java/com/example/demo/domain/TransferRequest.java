package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@SuperBuilder
public class TransferRequest {
    private Long toAccountNumber;
    private BigDecimal amount;
}
