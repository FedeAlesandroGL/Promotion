package com.example.promotion.controller;

import com.example.promotion.controller.impl.UserControllerImpl;
import com.example.promotion.dto.UserDto;
import com.example.promotion.service.UserService;
import com.example.promotion.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerImplTest {

  @Mock
  UserService userService;

  @InjectMocks
  UserControllerImpl userController;

  @Test
  void whenGetUsersThenOk() {
    List<UserDto> users = Collections.emptyList();
    when(this.userService.findAll()).thenReturn(users);

    assertEquals(ResponseEntity.ok(users), userController.getUsers());
  }

  @Test
  void whenGetUserThenOk() {
    UserDto userDto = new UserDto();
    when(this.userService.findByDocumentNumber(TestUtils.DOCUMENT_NUMBER)).thenReturn(userDto);

    assertEquals(ResponseEntity.ok(userDto), userController.getUser(TestUtils.DOCUMENT_NUMBER));
  }

  @Test
  void whenSaveThenOk() {
    UserDto userDto = new UserDto();
    when(this.userService.register(userDto)).thenReturn(userDto);

    assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(userDto), userController.register(userDto));
  }
}
