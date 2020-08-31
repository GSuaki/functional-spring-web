package com.suaki.functionalspring.functions.item;

import com.suaki.functionalspring.domain.Item;
import com.suaki.functionalspring.persistence.entities.ItemEntity;
import com.suaki.functionalspring.persistence.repositories.ItemRepository;
import io.vavr.Function1;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetItemsByInvoiceId implements Function1<String, List<Item>> {

  private final ItemRepository itemRepository;

  @Override
  public List<Item> apply(final String invoiceId) {
    return this.itemRepository.findAllByInvoiceId(invoiceId)
        .map(ItemEntity::toDomain);
  }
}