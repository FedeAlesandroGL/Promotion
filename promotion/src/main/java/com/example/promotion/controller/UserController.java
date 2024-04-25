package com.example.promotion.controller;

import com.example.promotion.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {

  ResponseEntity<List<UserDto>> getUsers();

  ResponseEntity<?> getUser(String documentNumber);

  ResponseEntity<UserDto> register(UserDto dto);
}
