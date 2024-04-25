package com.example.promotion.controller.impl;

import com.example.promotion.controller.UserController;
import com.example.promotion.dto.ErrorDto;
import com.example.promotion.dto.UserDto;
import com.example.promotion.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class UserControllerImpl implements UserController {

  private static final String DNI_NULL = "Dni should not be null";

  @Autowired
  private final UserService userService;

  @Override
  @GetMapping()
  @ApiOperation(value = "Get list of users", response = UserDto.class, tags = "getUsers")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK") })
  public ResponseEntity<List<UserDto>> getUsers() {
    List<UserDto> users = this.userService.findAll();

    return users.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(users) : ResponseEntity.ok().body(users);
  }

  @Override
  @GetMapping("/{documentNumber}")
  @ApiOperation(value = "Get an user by his document number", response = UserDto.class, tags = "getUser")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success|OK"),
      @ApiResponse(code = 400, message = "Document number is null!"),
      @ApiResponse(code = 404, message = "User not found!") })
  public ResponseEntity<?> getUser(@PathVariable String documentNumber) {
    if (documentNumber == null) {
      return ResponseEntity.badRequest().body(new ErrorDto(DNI_NULL));
    }
    return ResponseEntity.ok(this.userService.findByDocumentNumber(documentNumber));
  }

  @Override
  @PostMapping()
  @ApiOperation(value = "Register an user", response = UserDto.class, tags = "register")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "User registered!") })
  public ResponseEntity<UserDto> register(@RequestBody UserDto dto) {

    return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.register(dto));
  }

}
