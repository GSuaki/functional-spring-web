package com.suaki.functionalspring.errors;

import io.vavr.Function1;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ApiError {

  public static final Function1<Throwable, ApiError> fromThrowable = ApiError::new;
  public static final Function1<String, ApiError> fromMessage = ApiError::new;

  String message;

  private ApiError(final Throwable t) {
    this.message = t.getMessage();
  }
}
