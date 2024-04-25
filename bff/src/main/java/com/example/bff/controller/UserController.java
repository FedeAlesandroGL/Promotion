package com.example.bff.controller;

import com.example.bff.client.dto.UserDto;
import com.example.bff.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  @Autowired
  private final UserService userService;

  @GetMapping()
  public ResponseEntity<List<UserDto>> getUsers() {

    return userService.getUsers();
  }

  @GetMapping("/{documentNumber}")
  public ResponseEntity<?> getUser(@PathVariable String documentNumber) {
    return this.userService.findByDocumentNumber(documentNumber);
  }

  @PostMapping()
  public ResponseEntity<UserDto> register(@RequestBody UserDto dto) {

    return this.userService.register(dto);
  }
}
