package com.suaki.functionalspring.errors;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ApiError {

  String message;

  public ApiError(final Throwable t) {
    this.message = t.getMessage();
  }
}
