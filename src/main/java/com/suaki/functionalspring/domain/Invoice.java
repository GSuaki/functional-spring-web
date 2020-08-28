package com.suaki.functionalspring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Builder(toBuilder = true)
@Value
public class Invoice {

  String id;
  Integer number;
  Integer serie;
  Issuer issuer;
  Recipient recipient;
  List<Item> items;

  @JsonCreator
  public Invoice(
      @JsonProperty("number") final Integer number,
      @JsonProperty("serie") final Integer serie,
      @JsonProperty("issuer") final Issuer issuer,
      @JsonProperty("recipient") final Recipient recipient,
      @JsonProperty("items") final List<Item> items
  ) {
    this.id = null;
    this.number = number;
    this.serie = serie;
    this.issuer = issuer;
    this.recipient = recipient;
    this.items = items;
  }

  @JsonIgnore
  public String getIssuerCnpj() {
    return getIssuer().getCnpj();
  }

  @JsonIgnore
  public String getRecipientCpf() {
    return getRecipient().getCpf();
  }
}
