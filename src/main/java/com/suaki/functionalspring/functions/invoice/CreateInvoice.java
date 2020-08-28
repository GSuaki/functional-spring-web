package com.suaki.functionalspring.functions.invoice;

import com.suaki.functionalspring.domain.Invoice;
import com.suaki.functionalspring.domain.Invoice.InvoiceBuilder;
import com.suaki.functionalspring.domain.Issuer;
import com.suaki.functionalspring.domain.Item;
import com.suaki.functionalspring.domain.Recipient;
import com.suaki.functionalspring.functions.issuer.CreateIssuer;
import com.suaki.functionalspring.functions.item.CreateItems;
import com.suaki.functionalspring.functions.recipient.CreateRecipient;
import com.suaki.functionalspring.persistence.entities.InvoiceEntity;
import com.suaki.functionalspring.persistence.repositories.InvoiceRepository;
import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateInvoice implements Function1<Invoice, Try<Invoice>> {

  private final CreateIssuer createIssuer;
  private final CreateItems createItems;
  private final CreateRecipient createRecipient;
  private final InvoiceRepository invoiceRepository;

  @Override
  public Try<Invoice> apply(final Invoice body) {
    return createInvoice(body)
        .flatMap(createIssuer(body.getIssuer()))
        .flatMap(createItems(body.getItems()))
        .flatMap(createRecipient(body.getRecipient()));
  }

  private Function1<Invoice, Try<Invoice>> createIssuer(final Issuer body) {
    return invoice -> createIssuer.apply(body)
        .map(invoice.toBuilder()::issuer)
        .map(InvoiceBuilder::build);
  }

  private Function1<Invoice, Try<Invoice>> createItems(final List<Item> body) {
    return invoice -> Try.of(() -> createItems.apply(invoice.getId(), body))
        .map(invoice.toBuilder()::items)
        .map(InvoiceBuilder::build);
  }

  private Function1<Invoice, Try<Invoice>> createRecipient(final Recipient body) {
    return invoice -> createRecipient.apply(body)
        .map(invoice.toBuilder()::recipient)
        .map(InvoiceBuilder::build);
  }

  private Try<Invoice> createInvoice(Invoice invoice) {
    return Try.of(() -> InvoiceEntity.create(invoice))
        .map(this.invoiceRepository::save)
        .map(InvoiceEntity::toDomain);
  }
}