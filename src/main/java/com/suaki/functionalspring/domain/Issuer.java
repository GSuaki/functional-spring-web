package com.suaki.functionalspring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Issuer(
    @JsonProperty("cnpj")String cnpj,
    @JsonProperty("corporate_name")String corporateName
) {

  public static Issuer cnpj(final String cnpj) {
    return new Issuer(cnpj, "");
  }
}
