package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "protNFe")
public class Protocol {

    @JacksonXmlProperty(localName = "versao", isAttribute = true)
    String version;

    @JacksonXmlProperty(localName = "infProt")
    ProtocolInfo info;

}
