package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "emit")
public class Issuer {

    @JacksonXmlProperty(localName = "CNPJ")
    String cnpj;

    @JacksonXmlProperty(localName = "xNome")
    String name;

    @JacksonXmlProperty(localName = "xFant")
    String corporateName;

    @JacksonXmlProperty(localName = "IE")
    String ie;

    @JacksonXmlProperty(localName = "IEST")
    String iest;

    @JacksonXmlProperty(localName = "CRT")
    Integer crt;

    @JacksonXmlProperty(localName = "enderEmit")
    IssuerAddress address;
}
