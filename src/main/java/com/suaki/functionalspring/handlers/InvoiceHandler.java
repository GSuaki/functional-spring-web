package com.suaki.functionalspring.handlers;

import static org.springframework.web.servlet.function.ServerResponse.ok;
import static org.springframework.web.servlet.function.ServerResponse.status;

import com.suaki.functionalspring.domain.Invoice;
import com.suaki.functionalspring.errors.ApiError;
import com.suaki.functionalspring.functions.invoice.CreateInvoice;
import com.suaki.functionalspring.functions.invoice.DeleteInvoice;
import com.suaki.functionalspring.functions.invoice.FindAllInvoices;
import com.suaki.functionalspring.functions.invoice.GetInvoice;
import com.suaki.functionalspring.functions.invoice.UpdateInvoice;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
@RequiredArgsConstructor
public class InvoiceHandler {

  private final CreateInvoice createInvoice;
  private final DeleteInvoice deleteInvoice;
  private final FindAllInvoices findAllInvoices;
  private final GetInvoice getInvoice;
  private final UpdateInvoice updateInvoice;

  public ServerResponse create(final ServerRequest request) {
    return Try.of(() -> request.body(Invoice.class))
        .flatMap(this.createInvoice)
        .fold(ApiError.fromThrowable.andThen(status(500)::body), ok()::body);
  }

  public final ServerResponse findAll(final ServerRequest request) {
    return this.findAllInvoices
        .andThen(ok()::body)
        .get();
  }

  public ServerResponse get(final ServerRequest request) {
    return this.getInvoice.apply(request.pathVariable("id"))
        .fold(ApiError.fromString.andThen(status(404)::body), ok()::body);
  }

  public final ServerResponse update(final ServerRequest request) {
    return Try.of(() -> request.body(Invoice.class))
        .flatMap(payload -> this.updateInvoice.apply(request.pathVariable("id"), payload))
        .fold(ApiError.fromThrowable.andThen(status(500)::body), ok()::body);
  }

  public final ServerResponse delete(final ServerRequest request) {
    return this.deleteInvoice.apply(request.pathVariable("id"))
        .fold(ApiError.fromThrowable.andThen(status(500)::body), ok()::body);
  }
}
