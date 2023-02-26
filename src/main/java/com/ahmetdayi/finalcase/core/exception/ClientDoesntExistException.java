package com.ahmetdayi.finalcase.core.exception;

public class ClientDoesntExistException extends RuntimeException {
    public ClientDoesntExistException(String message) {
        super(message);
    }
}
