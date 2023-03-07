package com.gma.challenge.beruang.exception;

public class WalletNotFoundException extends InvalidRequestException {

    public WalletNotFoundException(String message) {
        super(message);
    }
}
