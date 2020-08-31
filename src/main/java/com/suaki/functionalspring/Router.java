package com.suaki.functionalspring;

import static org.springframework.web.servlet.function.RequestPredicates.DELETE;
import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RequestPredicates.POST;
import static org.springframework.web.servlet.function.RequestPredicates.PUT;
import static org.springframework.web.servlet.function.RouterFunctions.route;

import com.suaki.functionalspring.handlers.InvoiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class Router {

  private final InvoiceHandler invoiceHandler;

  @Bean
  public RouterFunction<ServerResponse> applicationRouter() {
    return pingRouter().and(invoicesRouter());
  }

  private RouterFunction<ServerResponse> pingRouter() {
    return route(GET("/ping"), req -> ServerResponse.ok().body("pong"));
  }

  public RouterFunction<ServerResponse> invoicesRouter() {
    return route(GET("/invoices"), invoiceHandler::findAll)
        .andRoute(POST("/invoices"), invoiceHandler::create)
        .andRoute(GET("/invoices/{id}"), invoiceHandler::get)
        .andRoute(PUT("/invoices/{id}"), invoiceHandler::update)
        .andRoute(DELETE("/invoices/{id}"), invoiceHandler::delete);
  }
}
