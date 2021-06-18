package com.example.demo.exceptions;

public class GeneralExceptionSchema {
    private String errorCode;
    private String errorMessage;
    private String referenceNumber;

    protected GeneralExceptionSchema() {}

    public GeneralExceptionSchema(String errorCode, String errorMessage, String referenceNumber) {
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
