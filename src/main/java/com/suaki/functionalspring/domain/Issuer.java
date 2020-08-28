package com.suaki.functionalspring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Issuer {

  String cnpj;
  String corporateName;

  @JsonCreator
  public Issuer(
      @JsonProperty("cnpj") final String cnpj,
      @JsonProperty("corporate_name") final String corporateName
  ) {
    this.cnpj = cnpj;
    this.corporateName = corporateName;
  }
}
