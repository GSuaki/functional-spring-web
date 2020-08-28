package com.suaki.functionalspring.functions.recipient;

import com.suaki.functionalspring.domain.Recipient;
import com.suaki.functionalspring.persistence.entities.RecipientEntity;
import com.suaki.functionalspring.persistence.repositories.RecipientRepository;
import io.vavr.Function1;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetRecipient implements Function1<String, Option<Recipient>> {

  private final RecipientRepository recipientRepository;

  @Override
  public Option<Recipient> apply(final String cnpj) {
    return this.recipientRepository.findById(cnpj)
        .map(RecipientEntity::toDomain);
  }
}
