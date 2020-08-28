package com.suaki.functionalspring.functions.recipient;

import com.suaki.functionalspring.domain.Recipient;
import com.suaki.functionalspring.persistence.entities.RecipientEntity;
import com.suaki.functionalspring.persistence.repositories.RecipientRepository;
import io.vavr.Function1;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateRecipient implements Function1<Recipient, Try<Recipient>> {

  private final RecipientRepository recipientRepository;

  @Override
  public Try<Recipient> apply(final Recipient recipient) {
    return Try.of(() -> RecipientEntity.valueOf(recipient))
        .map(this.recipientRepository::save)
        .map(RecipientEntity::toDomain);
  }
}
