package com.example.promotion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private String name;

  @JsonProperty(value = "last_name")
  private String lastName;

  @JsonProperty(value = "document_number")
  private String documentNumber;
}
