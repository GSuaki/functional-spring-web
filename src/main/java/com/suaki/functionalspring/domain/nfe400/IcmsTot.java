package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.math.BigDecimal;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ICMSTot")
public class IcmsTot {

    @JacksonXmlProperty(localName = "vFrete")
    BigDecimal vfrete;

    @JacksonXmlProperty(localName = "vProd")
    BigDecimal vprod;

    @JacksonXmlProperty(localName = "vNF")
    BigDecimal vnf;

    @JacksonXmlProperty(localName = "vST")
    BigDecimal vst;

    @JacksonXmlProperty(localName = "vICMS")
    BigDecimal vicms;
}