package com.suaki.functionalspring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Item {

  String id;
  String name;

  @JsonCreator
  public Item(
      @JsonProperty("id") final String id,
      @JsonProperty("name") final String name
  ) {
    this.id = id;
    this.name = name;
  }
}
