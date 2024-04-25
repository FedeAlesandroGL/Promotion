package com.example.promotion.exception.business;

public class UserExistsException extends BusinessException {

  public UserExistsException(String message) {
    super(message);
  }
}
