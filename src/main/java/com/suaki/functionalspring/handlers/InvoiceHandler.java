package com.suaki.functionalspring.handlers;

import static com.suaki.functionalspring.handlers.HandlerUtils.resolve;
import static com.suaki.functionalspring.handlers.HandlerUtils.resolveEither;
import static org.springframework.web.servlet.function.ServerResponse.ok;
import static org.springframework.web.servlet.function.ServerResponse.status;

import com.suaki.functionalspring.domain.Invoice;
import com.suaki.functionalspring.functions.invoice.CreateInvoice;
import com.suaki.functionalspring.functions.invoice.DeleteInvoice;
import com.suaki.functionalspring.functions.invoice.FindAllInvoices;
import com.suaki.functionalspring.functions.invoice.GetInvoice;
import com.suaki.functionalspring.functions.invoice.UpdateInvoice;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

  @SneakyThrows
  public ServerResponse create(final ServerRequest request) {
    return this.createInvoice
        .andThen(resolve(status(500)::body, ok()::body))
        .apply(request.body(Invoice.class));
  }

  public final ServerResponse findAll(final ServerRequest request) {
    return this.findAllInvoices
        .andThen(ok()::body)
        .apply();
  }

  public ServerResponse get(final ServerRequest request) {
    return this.getInvoice
        .andThen(resolveEither(ok()::body))
        .apply(request.pathVariable("id"));
  }

  public final ServerResponse update(final ServerRequest request) {
    return this.updateInvoice
        .andThen(resolve(status(500)::body, ok()::body))
        .apply(Invoice.builder().build());
  }

  public final ServerResponse delete(final ServerRequest request) {
    return this.deleteInvoice
        .andThen(resolve(status(500)::body, ok()::body))
        .apply(request.pathVariable("id"));
  }
}
