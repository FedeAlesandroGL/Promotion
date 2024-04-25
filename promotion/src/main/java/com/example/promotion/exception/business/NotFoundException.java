package com.example.promotion.exception.business;

import lombok.Getter;

@Getter
public class NotFoundException extends BusinessException {

  public NotFoundException(String message) {
    super(message);
  }
}
