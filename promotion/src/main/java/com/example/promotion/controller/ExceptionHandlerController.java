package com.example.promotion.controller;

import com.example.promotion.dto.ErrorDto;
import com.example.promotion.exception.business.NotFoundException;
import com.example.promotion.exception.business.UserExistsException;
import com.example.promotion.exception.technical.DbException;
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

  @ExceptionHandler(DbException.class)
  public ResponseEntity<ErrorDto> handleDbException(DbException exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorDto(exception.getMessage()));
  }

  @ExceptionHandler(UserExistsException.class)
  public ResponseEntity<ErrorDto> handleUserExistsException(UserExistsException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDto(exception.getMessage()));
  }
}
