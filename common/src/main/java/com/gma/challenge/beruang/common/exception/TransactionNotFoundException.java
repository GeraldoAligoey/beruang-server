package com.gma.challenge.beruang.common.exception;

public class TransactionNotFoundException extends InvalidRequestException {

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
