package com.suaki.functionalspring.persistence.entities;

import com.suaki.functionalspring.domain.Issuer;
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
@Table(name = "issuers")
public class IssuerEntity {

  @Id
  private String cnpj;
  private String corporateName;

  public static IssuerEntity valueOf(final Issuer issuer) {
    return IssuerEntity.builder()
        .cnpj(issuer.getCnpj())
        .corporateName(issuer.getCorporateName())
        .build();
  }

  public Issuer toDomain() {
    return Issuer.builder()
        .cnpj(getCnpj())
        .corporateName(getCorporateName())
        .build();
  }
}
