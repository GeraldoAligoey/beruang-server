package com.gma.challenge.beruang.exception;

public abstract class InvalidRequestException extends RuntimeException {

  public InvalidRequestException(String message) {
    super(message);
  }
}
