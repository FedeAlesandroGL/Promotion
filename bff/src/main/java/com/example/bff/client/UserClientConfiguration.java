package com.example.bff.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class UserClientConfiguration {

  @Bean
  ErrorDecoder feignErrorDecoder() {
    return new UserClientErrorDecoder();
  }
}
