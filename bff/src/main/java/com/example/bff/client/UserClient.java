package com.example.bff.client;

import com.example.bff.client.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "userClient", url = "${user.url}", configuration = UserClientConfiguration.class)
public interface UserClient {

  @GetMapping(value = "/user")
  ResponseEntity<List<UserDto>> getUsers();

  @GetMapping(value = "/user/{documentNumber}")
  ResponseEntity<?> getUser(@PathVariable String documentNumber);

  @PostMapping(value = "/user")
  ResponseEntity<UserDto> register(@RequestBody UserDto dto);
}
