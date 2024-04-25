package com.example.promotion.service;

import com.example.promotion.dto.UserDto;

import java.util.List;

public interface UserService {

  List<UserDto> findAll();

  UserDto findByDocumentNumber(String documentNumber);

  UserDto register(UserDto user);
}
