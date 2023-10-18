package com.example.kafkaconsumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

  @JsonProperty("num_1")
  private int num1;

  @JsonProperty("num_2")
  private int num2;

}
