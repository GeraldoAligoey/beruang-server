package com.gma.challenge.beruang.common.exception;

public abstract class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }
}
