package com.example.promotion.repository.impl;

import com.example.promotion.dto.UserDto;
import com.example.promotion.dto.converter.UserDtoConverter;
import com.example.promotion.exception.business.UserExistsException;
import com.example.promotion.exception.technical.DbException;
import com.example.promotion.model.User;
import com.example.promotion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepoImpl {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserRepoImpl.class);
  private static final String DATABASE_ERROR = "Database error";
  private static final String USER_EXISTS = "User already exists";

  @Autowired
  private final UserRepository userRepository;

  public List<UserDto> findAll() {
    try {
      return UserDtoConverter.getInstance().fromEntity(this.userRepository.findAll());
    } catch (JDBCException e) {
      LOGGER.error(DATABASE_ERROR);
      throw new DbException(DATABASE_ERROR);
    }
  }

  public Optional<UserDto> findByDocumentNumber(String documentNumber) {
    Optional<UserDto> userDto = Optional.empty();

    try {
      Optional<User> user = this.userRepository.findByDocumentNumber(documentNumber);
      if (user.isPresent()) {
        userDto = Optional.of((UserDtoConverter.getInstance().fromEntity(user.get())));
      }
    } catch (JDBCException e) {
      LOGGER.error(DATABASE_ERROR);
      throw new DbException(DATABASE_ERROR);
    }

    return userDto;
  }

  public UserDto save(User user) {
    if (this.userRepository.findByDocumentNumber(user.getDocumentNumber()).isPresent()) {
      throw new UserExistsException(USER_EXISTS);
    }

    try {
      return UserDtoConverter.getInstance().fromEntity(
          this.userRepository.save(user));
    } catch (JDBCException e) {
      LOGGER.error(DATABASE_ERROR);
      throw new DbException(DATABASE_ERROR);
    }
  }

}
