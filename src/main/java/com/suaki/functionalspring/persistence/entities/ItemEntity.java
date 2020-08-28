package com.suaki.functionalspring.persistence.entities;

import com.suaki.functionalspring.domain.Item;
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
@Table(name = "items")
public class ItemEntity {

  @Id
  private String id;
  private String name;
  private String itemId;
  private String invoiceId;

  public static ItemEntity create(final String invoiceId, final Item item) {
    return ItemEntity.builder()
        .id(UUID.randomUUID().toString())
        .itemId(item.getId())
        .invoiceId(invoiceId)
        .name(item.getName())
        .build();
  }

  public Item toDomain() {
    return Item.builder()
        .id(getItemId())
        .name(getName())
        .build();
  }
}
