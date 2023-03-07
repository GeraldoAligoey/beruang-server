package com.gma.challenge.beruang.exception.config;

import com.gma.challenge.beruang.exception.*;
import com.gma.challenge.beruang.generated.dto.GenericErrorModelData;
import com.gma.challenge.beruang.generated.dto.GenericErrorModelErrorsData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class BeruangExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GenericErrorModelData model = new GenericErrorModelData();
        GenericErrorModelErrorsData errors = new GenericErrorModelErrorsData();
        errors.setBody(List.of(ex.getMessage() == null ? ex.toString() : ex.getMessage()));
        model.setErrors(errors);

        if (status == HttpStatus.BAD_REQUEST) {
            return new ResponseEntity<>(model, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(model, status);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(
            CategoryNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<Object> handleWalletNotFoundException(
            WalletNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(IncompleteRequestDataException.class)
    public ResponseEntity<Object> handleIncompleteRequestDataException(
            IncompleteRequestDataException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, null, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(BudgetNotFoundException.class)
    public ResponseEntity<Object> handleBudgetNotFoundException(
            BudgetNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, null, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Object> handleTransactionNotFoundException(
            TransactionNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, null, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(CategoryNotInWalletException.class)
    public ResponseEntity<Object> handleCategoryNotInWalletException(
            CategoryNotInWalletException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, null, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
