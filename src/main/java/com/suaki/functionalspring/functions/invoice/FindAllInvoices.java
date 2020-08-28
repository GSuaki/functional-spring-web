package com.suaki.functionalspring.functions.invoice;

import com.suaki.functionalspring.domain.Invoice;
import com.suaki.functionalspring.persistence.entities.InvoiceEntity;
import com.suaki.functionalspring.persistence.repositories.InvoiceRepository;
import io.vavr.Function0;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllInvoices implements Function0<List<Invoice>> {

  private final InvoiceRepository invoiceRepository;

  @Override
  public List<Invoice> apply() {
    return this.invoiceRepository.findAll()
        .map(InvoiceEntity::toDomain);
  }
}