package com.suaki.functionalspring.functions.invoice;

import com.suaki.functionalspring.domain.Invoice;
import com.suaki.functionalspring.persistence.entities.InvoiceEntity;
import com.suaki.functionalspring.persistence.repositories.InvoiceRepository;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateInvoice implements Function1<Invoice, Try<Invoice>> {

  private final InvoiceRepository invoiceRepository;

  @Override
  public Try<Invoice> apply(final Invoice payload) {
    return this.invoiceRepository.findById(payload.getId())
        .toTry(() -> new RuntimeException("Not found"))
        .flatMap(Function2.of(this::updateInvoice).apply(payload));
  }

  private Try<Invoice> updateInvoice(final Invoice invoice, final InvoiceEntity entity) {
    return Try.of(() -> InvoiceEntity.valueOf(invoice))
        .map(this.invoiceRepository::save)
        .map(InvoiceEntity::toDomain);
  }
}
