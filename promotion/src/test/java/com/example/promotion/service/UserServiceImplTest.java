package com.example.promotion.service;

import com.example.promotion.dto.UserDto;
import com.example.promotion.exception.business.NotFoundException;
import com.example.promotion.model.User;
import com.example.promotion.repository.impl.UserRepoImpl;
import com.example.promotion.service.impl.UserServiceImpl;
import com.example.promotion.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  UserRepoImpl userRepository;

  @InjectMocks
  UserServiceImpl userService;

  @Test
  void whenFindAllThenOk() {
    List<UserDto> users = Collections.emptyList();
    when(this.userRepository.findAll()).thenReturn(users);

    assertEquals(users, this.userService.findAll());
  }

  @Test
  void whenFindByDocumentNumberThenOk() {
    UserDto user = new UserDto();
    when(this.userRepository.findByDocumentNumber(TestUtils.DOCUMENT_NUMBER)).thenReturn(Optional.of(user));

    assertEquals(user, this.userService.findByDocumentNumber(TestUtils.DOCUMENT_NUMBER));
  }

  @Test
  void whenFindByDocumentNumberThenThrowNotFoundException() {
    when(this.userRepository.findByDocumentNumber(TestUtils.DOCUMENT_NUMBER)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> this.userService.findByDocumentNumber(TestUtils.DOCUMENT_NUMBER));
  }

  @Test
  void whenRegisterThenOk() {

    UserDto userDto = new UserDto();

    when(this.userRepository.save(new User())).thenReturn(userDto);

    assertEquals(userDto, this.userService.register(userDto));
  }
}
