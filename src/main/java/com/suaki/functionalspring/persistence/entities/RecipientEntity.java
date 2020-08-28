package com.suaki.functionalspring.persistence.entities;

import com.suaki.functionalspring.domain.Issuer;
import com.suaki.functionalspring.domain.Recipient;
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
@Table(name = "recipients")
public class RecipientEntity {

  @Id
  private String cpf;
  private String name;

  public static RecipientEntity valueOf(final Recipient recipient) {
    return RecipientEntity.builder()
        .cpf(recipient.getCpf())
        .name(recipient.getName())
        .build();
  }

  public Recipient toDomain() {
    return Recipient.builder()
        .cpf(getCpf())
        .name(getName())
        .build();
  }
}
