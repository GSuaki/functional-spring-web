package com.suaki.functionalspring.persistence.entities;

import com.suaki.functionalspring.domain.Invoice;
import com.suaki.functionalspring.domain.Issuer;
import com.suaki.functionalspring.domain.Recipient;
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
    return Invoice.builder()
        .id(getId())
        .number(getNumber())
        .serie(getSerie())
        .issuer(Issuer.builder()
            .cnpj(getIssuer())
            .build())
        .recipient(Recipient.builder()
            .cpf(getRecipient())
            .build())
        .build();
  }

  public static InvoiceEntity create(final Invoice invoice) {
    return valueOf(invoice).toBuilder()
        .id(UUID.randomUUID().toString())
        .issuer(invoice.getIssuer().getCnpj())
        .recipient(invoice.getRecipient().getCpf())
        .build();
  }

  public static InvoiceEntity valueOf(final Invoice invoice) {
    return InvoiceEntity.builder()
        .id(invoice.getId())
        .number(invoice.getNumber())
        .serie(invoice.getSerie())
        .issuer(invoice.getIssuer().getCnpj())
        .recipient(invoice.getRecipient().getCpf())
        .build();
  }
}
