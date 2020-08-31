package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "det")
public class Item {

  private static Integer MAX_ITEM_NAME_LKENGTH = 32;
  private static Integer MAX_ITEM_CODE_LKENGTH = 4;
  private static Integer MAX_ITEM_UNIT_LKENGTH = 2;

  @JacksonXmlProperty(localName = "nItem", isAttribute = true)
  Integer number;

  @JacksonXmlProperty(localName = "prod")
  Product product;

  @JacksonXmlProperty(localName = "infAdProd")
  String moreInfo;
}
