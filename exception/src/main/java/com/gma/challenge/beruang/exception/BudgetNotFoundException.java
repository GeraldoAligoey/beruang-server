package com.gma.challenge.beruang.exception;

public class BudgetNotFoundException extends InvalidRequestException {

    public BudgetNotFoundException(String message) {
        super(message);
    }
}
