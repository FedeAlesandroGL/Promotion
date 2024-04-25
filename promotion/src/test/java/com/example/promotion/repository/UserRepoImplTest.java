package com.example.promotion.repository;

import com.example.promotion.dto.converter.UserDtoConverter;
import com.example.promotion.exception.technical.DbException;
import com.example.promotion.model.User;
import com.example.promotion.repository.impl.UserRepoImpl;
import com.example.promotion.utils.TestUtils;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepoImplTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserRepoImpl userRepositoryImpl;

  @Test
  void whenFindAllThenOk() {
    List<User> users = new ArrayList<>();

    when(this.userRepository.findAll()).thenReturn(users);

    assertEquals(UserDtoConverter.getInstance().fromEntity(users), this.userRepositoryImpl.findAll());
  }

  @Test
  void whenFindAllThenThrowDbException() {
    when(this.userRepository.findAll()).thenThrow(JDBCException.class);

    assertThrows(DbException.class, () -> this.userRepositoryImpl.findAll());
  }

  @Test
  void whenFindByDocumentNumberThenOk() {
    User user = new User();
    when(this.userRepository.findByDocumentNumber(TestUtils.DOCUMENT_NUMBER)).thenReturn(Optional.of(user));

    assertEquals(Optional.of(UserDtoConverter.getInstance().fromEntity(user)),
        this.userRepositoryImpl.findByDocumentNumber(TestUtils.DOCUMENT_NUMBER));
  }

  @Test
  void whenFindByDocumentNumberThenThrowDbException() {
    when(this.userRepository.findByDocumentNumber(TestUtils.DOCUMENT_NUMBER)).thenThrow(JDBCException.class);

    assertThrows(DbException.class, () -> this.userRepositoryImpl.findByDocumentNumber(TestUtils.DOCUMENT_NUMBER));
  }

  @Test
  void whenSaveThenOk() {
    User user = new User();

    when(this.userRepository.save(user)).thenReturn(user);

    assertEquals(UserDtoConverter.getInstance().fromEntity(user), this.userRepositoryImpl.save(user));
  }

  @Test
  void whenSaveThenThrowDbException() {
    User user = new User();

    when(this.userRepository.save(user)).thenThrow(JDBCException.class);

    assertThrows(DbException.class, () -> this.userRepositoryImpl.save(user));
  }
}
