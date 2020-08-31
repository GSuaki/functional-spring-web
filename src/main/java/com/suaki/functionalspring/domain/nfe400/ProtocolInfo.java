package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "infProt")
public class ProtocolInfo {

    @JacksonXmlProperty(localName = "chNFe")
    String fiscalKey;

    @JacksonXmlProperty(localName = "tpAmb")
    Integer environment;

    @JacksonXmlProperty(localName = "verAplic")
    String appVersion;

    @JacksonXmlProperty(localName = "dhRecbto")
    String receiptDate;

    @JacksonXmlProperty(localName = "nProt")
    String protocolNumber;

    @JacksonXmlProperty(localName = "digVal")
    String validatorDigit;

    @JacksonXmlProperty(localName = "cStat")
    Integer stat;

    @JacksonXmlProperty(localName = "xMotivo")
    String motive;
}
