package com.suaki.functionalspring.functions.item;

import com.suaki.functionalspring.domain.Item;
import com.suaki.functionalspring.persistence.entities.ItemEntity;
import com.suaki.functionalspring.persistence.repositories.ItemRepository;
import io.vavr.Function2;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateItems implements Function2<String, List<Item>, List<Item>> {

  private final ItemRepository itemRepository;

  @Override
  public List<Item> apply(final String invoiceId, final List<Item> items) {
    return items.map(Function2.of(ItemEntity::create).apply(invoiceId))
        .map(this.itemRepository::save)
        .map(ItemEntity::toDomain);
  }
}
