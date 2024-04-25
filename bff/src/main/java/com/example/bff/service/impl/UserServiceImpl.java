package com.example.bff.service.impl;

import com.example.bff.client.UserClient;
import com.example.bff.client.dto.UserDto;
import com.example.bff.client.exception.NotFoundException;
import com.example.bff.service.UserService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  // todo tratar excepciones

  @Autowired
  private final UserClient userClient;

  @Override public ResponseEntity<List<UserDto>> getUsers() {
      return userClient.getUsers();
  }

  @Override public ResponseEntity<?> findByDocumentNumber(String documentNumber) {
      return userClient.getUser(documentNumber);

  }

  @Override public ResponseEntity<UserDto> register(UserDto dto) {
      return userClient.register(dto);

  }
}
