package com.suaki.functionalspring.functions.invoice;

import com.suaki.functionalspring.domain.Invoice;
import com.suaki.functionalspring.functions.issuer.GetIssuer;
import com.suaki.functionalspring.functions.item.GetItemsByInvoiceId;
import com.suaki.functionalspring.functions.recipient.GetRecipient;
import com.suaki.functionalspring.persistence.entities.InvoiceEntity;
import com.suaki.functionalspring.persistence.repositories.InvoiceRepository;
import io.vavr.Function1;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetInvoice implements Function1<String, Either<String, Invoice>> {

  private final InvoiceRepository invoiceRepository;
  private final GetIssuer getIssuer;
  private final GetItemsByInvoiceId getItemsByInvoiceId;
  private final GetRecipient getRecipient;

  @Override
  public Either<String, Invoice> apply(final String id) {
    return getInvoice(id)
        .flatMap(this::assembleIssuer)
        .map(this::assembleItems)
        .flatMap(this::assembleRecipient);
  }

  private Either<String, Invoice> assembleIssuer(final Invoice invoice) {
    return getIssuer.apply(invoice.getIssuerCnpj())
        .map(invoice::issuer)
        .toEither(() -> "Issuer not found");
  }

  private Either<String, Invoice> assembleRecipient(final Invoice invoice) {
    return getRecipient.apply(invoice.getRecipientCpf())
        .map(invoice::recipient)
        .toEither(() -> "Recipient not found");
  }

  private Invoice assembleItems(final Invoice invoice) {
    return invoice.items(getItemsByInvoiceId.apply(invoice.id()));
  }

  private Either<String, Invoice> getInvoice(String id) {
    return this.invoiceRepository.findById(id)
        .map(InvoiceEntity::toDomain)
        .toEither(() -> "Invoice not found");
  }
}
