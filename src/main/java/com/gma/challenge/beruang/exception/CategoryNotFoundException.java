package com.gma.challenge.beruang.exception;

public class CategoryNotFoundException extends InvalidRequestException {

  public CategoryNotFoundException(String message) {
    super(message);
  }
}
