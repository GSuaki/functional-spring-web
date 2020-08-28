package com.suaki.functionalspring.functions.issuer;

import com.suaki.functionalspring.domain.Issuer;
import com.suaki.functionalspring.persistence.entities.IssuerEntity;
import com.suaki.functionalspring.persistence.repositories.IssuerRepository;
import io.vavr.Function1;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetIssuer implements Function1<String, Option<Issuer>> {

  private final IssuerRepository issuerRepository;

  @Override
  public Option<Issuer> apply(final String cnpj) {
    return this.issuerRepository.findById(cnpj)
        .map(IssuerEntity::toDomain);
  }
}
