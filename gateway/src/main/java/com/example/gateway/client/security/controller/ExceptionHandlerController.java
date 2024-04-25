package com.example.gateway.client.security.controller;

import com.example.gateway.client.security.dto.ErrorDto;
import com.example.gateway.client.security.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(AuthException.class)
  public ResponseEntity<ErrorDto> handleAuthException(AuthException exception) {
    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body(new ErrorDto(exception.getMessage()));
  }
}
