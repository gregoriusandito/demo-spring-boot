package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class TransferException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private final String referenceNumber;

    public TransferException(String errorCode, String errorMessage, String referenceNumber) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.referenceNumber = referenceNumber;
    }
}
