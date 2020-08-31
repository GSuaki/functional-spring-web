package com.suaki.functionalspring.persistence.entities;

import com.suaki.functionalspring.domain.Invoice;
import com.suaki.functionalspring.domain.Issuer;
import com.suaki.functionalspring.domain.Recipient;
import io.vavr.collection.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "invoices")
public class InvoiceEntity {

  @Id
  private String id;
  private Integer number;
  private Integer serie;
  private String issuer;
  private String recipient;

  public Invoice toDomain() {
    return new Invoice(
        getId(),
        getNumber(),
        getSerie(),
        Issuer.cnpj(getIssuer()),
        Recipient.cpf(getRecipient()),
        List.empty()
    );
  }

  public static InvoiceEntity create(final Invoice invoice) {
    return valueOf(invoice).toBuilder()
        .id(UUID.randomUUID().toString())
        .issuer(invoice.issuer().cnpj())
        .recipient(invoice.recipient().cpf())
        .build();
  }

  public static InvoiceEntity valueOf(final Invoice invoice) {
    return InvoiceEntity.builder()
        .number(invoice.number())
        .serie(invoice.serie())
        .issuer(invoice.issuer().cnpj())
        .recipient(invoice.recipient().cpf())
        .build();
  }
}
