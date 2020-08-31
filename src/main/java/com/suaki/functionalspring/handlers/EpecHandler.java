package com.suaki.functionalspring.handlers;

import static org.springframework.web.servlet.function.ServerResponse.ok;
import static org.springframework.web.servlet.function.ServerResponse.status;

import com.suaki.functionalspring.errors.ApiError;
import com.suaki.functionalspring.functions.epec.AuthorizeEpec;
import com.suaki.functionalspring.functions.epec.ConfigurateEpec;
import com.suaki.functionalspring.functions.epec.CreateEpecFromXml;
import io.vavr.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
@RequiredArgsConstructor
public class EpecHandler {

  private final AuthorizeEpec authorizeEpec;
  private final CreateEpecFromXml createEpecFromXml;
  private final ConfigurateEpec configurateEpec;

  @SneakyThrows
  public ServerResponse authorize(final ServerRequest request) {
    return this.configurateEpec.get()
        .flatMapTry(config -> this.createEpecFromXml.apply(request.body(String.class)).map(evento -> Tuple.of(evento, config)))
        .flatMap(tuple -> this.authorizeEpec.apply(tuple._1, tuple._2))
        .fold(ApiError.fromThrowable.andThen(status(500)::body), ok()::body);
  }
}
