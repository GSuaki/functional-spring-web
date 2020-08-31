package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.math.BigDecimal;

@JacksonXmlRootElement(localName = "prod")
public class Product {

    @JacksonXmlProperty(localName = "CFOP")
    String cfop;

    @JacksonXmlProperty(localName = "cProd")
    String code;

    @JacksonXmlProperty(localName = "cEAN")
    String eanCode;

    @JacksonXmlProperty(localName = "xProd")
    String name;

    @JacksonXmlProperty(localName = "NCM")
    String ncm;

    @JacksonXmlProperty(localName = "uCom")
    String unity;

    @JacksonXmlProperty(localName = "qCom")
    String quantity;

    @JacksonXmlProperty(localName = "vUnCom")
    BigDecimal vUnCom;

    @JacksonXmlProperty(localName = "vProd")
    BigDecimal amount;

    @JacksonXmlProperty(localName = "cEANTrib")
    String eanTributes;

    @JacksonXmlProperty(localName = "uTrib")
    String uTrib;

    @JacksonXmlProperty(localName = "qTrib")
    String qTrib;

    @JacksonXmlProperty(localName = "vUnTrib")
    String vUnTrib;

    @JacksonXmlProperty(localName = "vFrete")
    String vFrete;

    @JacksonXmlProperty(localName = "indTot")
    String indTot;
}
