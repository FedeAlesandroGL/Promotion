package com.example.promotion.dto.converter;

import com.example.promotion.dto.UserDto;
import com.example.promotion.model.User;

public class UserDtoConverter implements Converter<User, UserDto> {

  private static UserDtoConverter instance = null;

  private UserDtoConverter() {
  }

  public static UserDtoConverter getInstance() {
    if (instance == null) {
      instance = new UserDtoConverter();
    }
    return instance;
  }

  @Override
  public User fromDto(UserDto dto) {
    return User.builder().name(dto.getName()).lastName(dto.getLastName()).documentNumber(dto.getDocumentNumber())
        .build();
  }

  @Override public UserDto fromEntity(User entity) {
    return UserDto.builder().name(entity.getName()).lastName(entity.getLastName())
        .documentNumber(entity.getDocumentNumber()).build();
  }
}
