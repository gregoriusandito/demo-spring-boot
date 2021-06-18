package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class GeneralException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    private String referenceNumber;

    protected GeneralException() {}

    public GeneralException(String errorCode, String errorMessage, String referenceNumber) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.referenceNumber = referenceNumber;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
