package com.suaki.functionalspring.utils;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.jackson.datatype.VavrModule;
import java.text.SimpleDateFormat;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public final class JsonUtils {

  private static final String ISO_8601_24H_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

  public static final ObjectMapper mapper = Jackson2ObjectMapperBuilder.json()
      .modules(new VavrModule(), new JavaTimeModule(), new Jdk8Module())
      .createXmlMapper(false)
      .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
      .featuresToDisable(FAIL_ON_UNKNOWN_PROPERTIES, WRITE_DATES_AS_TIMESTAMPS)
      .dateFormat(new SimpleDateFormat(ISO_8601_24H_FULL_FORMAT))
      .build();
}
