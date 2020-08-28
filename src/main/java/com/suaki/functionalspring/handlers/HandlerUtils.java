package com.suaki.functionalspring.handlers;

import com.suaki.functionalspring.errors.ApiError;
import io.vavr.Function1;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.function.ServerResponse;

@Slf4j
public final class HandlerUtils {

  public static Consumer<Throwable> logger = t -> log.error("Error handler", t);

  public static <T> Function1<Try<T>, ServerResponse> resolve(
      final Function1<ApiError, ServerResponse> leftMapper,
      final Function1<T, ServerResponse> rightMapper
  ) {
    return data -> data.map(rightMapper)
        .onFailure(logger)
        .getOrElseGet(leftMapper.compose(ApiError::new));
  }

  public static <T> Function1<Option<T>, ServerResponse> resolve(
      final Function1<T, ServerResponse> rightMapper
  ) {
    return data -> data.map(rightMapper)
        .getOrElse(ServerResponse.notFound()::build);
  }

  public static <T> Function1<Either<String, T>, ServerResponse> resolveEither(
      final Function1<T, ServerResponse> rightMapper
  ) {
    return data -> data.fold(
        str -> ServerResponse.badRequest().body(new ApiError(str)),
        rightMapper
    );
  }
}
