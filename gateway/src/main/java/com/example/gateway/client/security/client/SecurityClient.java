package com.example.gateway.client.security.client;

import com.example.gateway.client.security.request.JwtRequest;
import com.example.gateway.client.security.response.JwtResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "securityClient", url = "${security.url}")
public interface SecurityClient {

  @PostMapping(value = "/authenticate")
  JwtResponse authenticate(JwtRequest request);

  @PostMapping(value = "/validate")
  JwtResponse validate(@RequestHeader("Authorization") String bearerToken);
}
