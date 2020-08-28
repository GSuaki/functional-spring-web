package com.suaki.functionalspring.functions.invoice;

import com.suaki.functionalspring.persistence.repositories.InvoiceRepository;
import io.vavr.Function1;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteInvoice implements Function1<String, Try<Void>> {

  private final InvoiceRepository invoiceRepository;

  @Override
  public Try<Void> apply(final String id) {
    return Try.run(() -> this.invoiceRepository.deleteById(id));
  }
}
