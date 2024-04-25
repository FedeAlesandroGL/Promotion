package com.example.promotion.utils;

import com.example.promotion.dto.UserDto;
import com.example.promotion.dto.converter.UserDtoConverter;
import com.example.promotion.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class TestUtils {

  public static final String DOCUMENT_NUMBER = "1234567";

  public static List<UserDto> userListToDto(List<User> users) {
    return users.stream()
        .map(UserDtoConverter.getInstance()::fromEntity)
        .collect(Collectors.toList());
  }

  public static UserDto userToDto(User user) {
    return UserDtoConverter.getInstance().fromEntity(user);
  }
}
