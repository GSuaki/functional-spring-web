package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "infNFe")
public class NFeInfo {

  @JacksonXmlProperty(localName = "Id", isAttribute = true)
  String id;

  @JacksonXmlProperty(localName = "versao", isAttribute = true)
  String version;

  @JacksonXmlProperty(localName = "dest")
  Recipient recipient;

  @JacksonXmlProperty(localName = "det")
  List<Item> items;

  @JacksonXmlProperty(localName = "ide")
  Identification identification;

  @JacksonXmlProperty(localName = "emit")
  Issuer issuer;

  @JacksonXmlProperty(localName = "total")
  FiscalData fiscalData;

  @JacksonXmlProperty(localName = "infAdic")
  AdditionalInfo infoAdicional;
}