package com.suaki.functionalspring.functions.epec;

import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.EventoEpec;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import com.suaki.functionalspring.domain.nfe400.IcmsTot;
import com.suaki.functionalspring.domain.nfe400.NFeInfo;
import com.suaki.functionalspring.domain.nfe400.NfeProcessRoot;
import com.suaki.functionalspring.utils.XmlUtils;
import io.vavr.Function1;
import io.vavr.control.Option;
import io.vavr.control.Try;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateEpecFromXml implements Function1<String, Try<Evento>> {

  @Override
  public Try<Evento> apply(final String xml) {
    return Try.of(() -> XmlUtils.unmarshall(xml.getBytes(StandardCharsets.UTF_8), NfeProcessRoot.class))
        .map(this::assembleEvento);
  }

  private Evento assembleEvento(final NfeProcessRoot nfe) {
    final NFeInfo info = nfe.getNfe().getInfo();
    final Function1<Function<IcmsTot, BigDecimal>, String> getIcmsValue = mapper -> Option.of(info.getFiscalData().getIcmsTotal())
        .map(mapper)
        .map(BigDecimal::toString)
        .getOrElse("");

    //Preenche os Dados do Evento EPEC
    final EventoEpec eventoEpec = new EventoEpec();
    eventoEpec.setCnpjDestinatario(Option.of(info.getRecipient().getCnpj()).getOrElse(() -> info.getRecipient().getCpf()));
    eventoEpec.setIeDestinatario(info.getRecipient().getStateTaxId());
    eventoEpec.setEstadoDestinatario(EstadosEnum.SP);
    eventoEpec.setIeEmitente(info.getIssuer().getIe());
    eventoEpec.setTipoNF(info.getIdentification().getTpNf().toString());
    eventoEpec.setvST(getIcmsValue.apply(IcmsTot::getVst));
    eventoEpec.setvNF(getIcmsValue.apply(IcmsTot::getVnf));
    eventoEpec.setvICMS(getIcmsValue.apply(IcmsTot::getVicms));

    //Agora o evento pode aceitar uma lista de cancelaemntos para envio em Lote.
    //Para isso Foi criado o Objeto Epec
    final Evento epec = new Evento();
    epec.setChave(nfe.getProtocol().getInfo().getFiscalKey());
    epec.setCnpj(info.getIssuer().getCnpj());
    epec.setDataEvento(LocalDateTime.now());
    epec.setEventoEpec(eventoEpec);

    return epec;
  }
}
