package com.example.demo.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class TransferRequest {
    private long toAccountNumber;
    private BigDecimal amount;
}
