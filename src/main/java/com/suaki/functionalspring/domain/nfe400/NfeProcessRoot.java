package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "nfeProc")
public class NfeProcessRoot {

  private static Integer MAX_NAME_LKENGTH = 40;
  private static Integer MAX_ADDITIONAL_INFO_LKENGTH = 75;
  private static String MAX_ADDITIONAL_INFO_LKENGTH_REGEX = "(?<=\\G.{75})";
  private static Integer TRANSACTION_TYPE_INPUT = 0;
  private static Integer MAX_ITEMS_SIZE = 8;
  private static Integer MAX_ADDITIONAL_INFO_SIZE = 6;

  @JacksonXmlProperty(localName = "versao", isAttribute = true)
  String version;

  @JacksonXmlProperty(localName = "NFe")
  Nfe nfe;

  @JacksonXmlProperty(localName = "protNFe")
  Protocol protocol;
}
