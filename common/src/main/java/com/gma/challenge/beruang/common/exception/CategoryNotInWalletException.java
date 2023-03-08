package com.gma.challenge.beruang.common.exception;

public class CategoryNotInWalletException extends InvalidRequestException {

    public CategoryNotInWalletException(String message) {
        super(message);
    }
}
