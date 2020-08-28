package com.suaki.functionalspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suaki.functionalspring.utils.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

  @Bean
  public ObjectMapper mapper() {
    return JsonUtils.mapper;
  }
}
