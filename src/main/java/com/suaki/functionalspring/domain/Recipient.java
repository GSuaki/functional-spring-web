package com.suaki.functionalspring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Recipient {

  String cpf;
  String name;

  @JsonCreator
  public Recipient(
      @JsonProperty("cpf") final String cpf,
      @JsonProperty("name") final String name
  ) {
    this.cpf = cpf;
    this.name = name;
  }
}
