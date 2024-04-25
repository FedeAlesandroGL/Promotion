package com.example.bff.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {

  private String name;

  @JsonProperty(value = "last_name")
  private String lastName;

  @JsonProperty(value = "document_number")
  private String documentNumber;
}
