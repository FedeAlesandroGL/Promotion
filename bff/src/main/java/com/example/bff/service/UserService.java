package com.example.bff.service;

import com.example.bff.client.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

  ResponseEntity<List<UserDto>> getUsers();

  ResponseEntity<?> findByDocumentNumber(@PathVariable String documentNumber);

  ResponseEntity<UserDto> register(@RequestBody UserDto dto);
}
