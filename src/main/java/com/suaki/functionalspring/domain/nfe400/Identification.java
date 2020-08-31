package com.suaki.functionalspring.domain.nfe400;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ide")
public class Identification {

    @JacksonXmlProperty(localName = "cUF")
    Integer state;

    @JacksonXmlProperty(localName = "cNF")
    String nf;

    @JacksonXmlProperty(localName = "natOp")
    String natureOfOperation;

    @JacksonXmlProperty(localName = "mod")
    Integer mod;

    @JacksonXmlProperty(localName = "serie")
    Integer serie;

    @JacksonXmlProperty(localName = "nNF")
    Integer nNf;

    @JacksonXmlProperty(localName = "dhEmi")
    String emissionDate;

    @JacksonXmlProperty(localName = "dhSaiEnt")
    String exitDate;

    @JacksonXmlProperty(localName = "tpNF")
    Integer tpNf;

    @JacksonXmlProperty(localName = "idDest")
    Integer destinationOperation;

    @JacksonXmlProperty(localName = "cMunFG")
    Integer cMunFg;

    @JacksonXmlProperty(localName = "tpImp")
    Integer tpImp;

    @JacksonXmlProperty(localName = "tpEmis")
    Integer tpEmis;

    @JacksonXmlProperty(localName = "cDV")
    Integer cDv;

    @JacksonXmlProperty(localName = "tpAmb")
    Integer tpAmb;

    @JacksonXmlProperty(localName = "finNFe")
    Integer finNfe;

    @JacksonXmlProperty(localName = "indFinal")
    Integer indFinal;

    @JacksonXmlProperty(localName = "indPres")
    Integer indPres;

    @JacksonXmlProperty(localName = "procEmi")
    Integer procEmi;

    @JacksonXmlProperty(localName = "verProc")
    String verProc;

    @JacksonXmlProperty(localName = "dhCont")
    String dhCont;

    @JacksonXmlProperty(localName = "xJust")
    String xJust;
}
