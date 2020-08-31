package com.suaki.functionalspring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vavr.collection.List;

public record Invoice(
    @JsonProperty("id")String id,
    @JsonProperty("number")Integer number,
    @JsonProperty("serie")Integer serie,
    @JsonProperty("issuer")Issuer issuer,
    @JsonProperty("recipient")Recipient recipient,
    @JsonProperty("items")List<Item>items
) {

  @JsonIgnore
  public String getIssuerCnpj() {
    return issuer().cnpj();
  }

  @JsonIgnore
  public String getRecipientCpf() {
    return recipient().cpf();
  }

  @JsonIgnore
  public Invoice issuer(final Issuer issuer) {
    return new Invoice(id, number, serie, issuer, recipient, items);
  }

  @JsonIgnore
  public Invoice recipient(final Recipient recipient) {
    return new Invoice(id, number, serie, issuer, recipient, items);
  }

  @JsonIgnore
  public Invoice items(final List<Item> items) {
    return new Invoice(id, number, serie, issuer, recipient, items);
  }
}
