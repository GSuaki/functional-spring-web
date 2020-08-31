package com.suaki.functionalspring.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vavr.Function1;

public record ApiError(@JsonProperty("message") String message) {

  public static final Function1<Throwable, ApiError> fromThrowable = ApiError::new;
  public static final Function1<String, ApiError> fromString = ApiError::new;

  public ApiError(final Throwable t) {
    this(t.getMessage());
  }
}
