package com.gma.challenge.beruang.common.exception;

public class CategoryNotFoundException extends InvalidRequestException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
