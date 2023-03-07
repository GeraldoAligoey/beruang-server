package com.gma.challenge.beruang.exception;

public class TransactionNotFoundException extends InvalidRequestException {

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
