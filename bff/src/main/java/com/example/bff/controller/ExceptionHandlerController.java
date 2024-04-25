package com.example.bff.controller;

import com.example.bff.client.exception.NotFoundException;
import com.example.bff.client.exception.BadRequestException;
import com.example.bff.client.exception.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorDto(exception.getMessage()));
  }


  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorDto> handleUserExistsException(BadRequestException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDto(exception.getMessage()));
  }
}
