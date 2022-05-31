package com.gma.challenge.beruang.exception.config;

import java.util.List;

import com.gma.challenge.beruang.data.GenericErrorModelData;
import com.gma.challenge.beruang.data.GenericErrorModelErrorsData;
import com.gma.challenge.beruang.exception.CategoryNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BeruangExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
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
  public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest request) {
    return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

}