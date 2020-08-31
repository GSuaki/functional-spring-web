package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "enderDest")
public class RecipientAddress {

    @JacksonXmlProperty(localName = "xLgr")
    String street;

    @JacksonXmlProperty(localName = "nro")
    String number;

    @JacksonXmlProperty(localName = "xCpl")
    String complement;

    @JacksonXmlProperty(localName = "xBairro")
    String neightboorHood;

    @JacksonXmlProperty(localName = "CEP")
    String zipCode;

    @JacksonXmlProperty(localName = "cMun")
    String cityCode;

    @JacksonXmlProperty(localName = "xMun")
    String city;

    @JacksonXmlProperty(localName = "UF")
    String state;

    @JacksonXmlProperty(localName = "xPais")
    String country;

    @JacksonXmlProperty(localName = "cPais")
    String countryCode;

    @JacksonXmlProperty(localName = "fone")
    String phone;
}
