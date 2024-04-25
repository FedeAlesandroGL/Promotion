package com.example.promotion.service.impl;

import com.example.promotion.dto.UserDto;
import com.example.promotion.dto.converter.UserDtoConverter;
import com.example.promotion.exception.business.NotFoundException;
import com.example.promotion.repository.impl.UserRepoImpl;
import com.example.promotion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String USER_NOT_FOUND = "We weren't able to find a user with that document number";
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private final UserRepoImpl userRepository;
  
  @Override
  public List<UserDto> findAll() {
    return this.userRepository.findAll();
  }

  @Override
  public UserDto findByDocumentNumber(String documentNumber) {
    return this.userRepository.findByDocumentNumber(documentNumber).orElseThrow(() -> {
      LOGGER.error(USER_NOT_FOUND);
      throw new NotFoundException(USER_NOT_FOUND);
    });
  }

  @Override
  public UserDto register(UserDto dto) {
    return this.userRepository.save(UserDtoConverter.getInstance().fromDto(dto));
  }
}
