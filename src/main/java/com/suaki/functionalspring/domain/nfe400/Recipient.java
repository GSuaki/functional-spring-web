package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "dest")
public class Recipient {

  @JacksonXmlProperty(localName = "IE")
  String stateTaxId;

  @JacksonXmlProperty(localName = "CPF")
  String cpf;

  @JacksonXmlProperty(localName = "CNPJ")
  String cnpj;

  @JacksonXmlProperty(localName = "xNome")
  String name;

  @JacksonXmlProperty(localName = "enderDest")
  RecipientAddress address;

  @JacksonXmlProperty(localName = "indIEDest")
  Integer indIEDest;
}