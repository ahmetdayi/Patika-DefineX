package com.ahmetdayi.finalcase.core.exception;

public class CreditDoesntExistException extends RuntimeException {
    public CreditDoesntExistException(String message) {
        super(message);
    }
}
