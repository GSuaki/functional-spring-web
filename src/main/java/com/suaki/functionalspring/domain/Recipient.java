package com.suaki.functionalspring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Recipient(
    @JsonProperty("cpf") String cpf,
    @JsonProperty("name") String name
) {

  public static Recipient cpf(final String cpf) {
    return new Recipient(cpf, "");
  }
}
