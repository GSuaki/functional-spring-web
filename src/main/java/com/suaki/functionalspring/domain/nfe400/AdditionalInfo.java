package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "infAdic")
public class AdditionalInfo {

    @JacksonXmlProperty(localName = "infAdFisco")
    String fiscoInfo;

    @JacksonXmlProperty(localName = "infCpl")
    String cplInfo;

}
