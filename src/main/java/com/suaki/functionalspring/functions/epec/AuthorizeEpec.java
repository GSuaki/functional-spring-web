package com.suaki.functionalspring.functions.epec;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento;
import br.com.swconsultoria.nfe.util.EpecUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import io.vavr.Function2;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizeEpec implements Function2<Evento, ConfiguracoesNfe, Try<TRetEnvEvento>> {

  @Override
  public Try<TRetEnvEvento> apply(final Evento epec, final ConfiguracoesNfe config) {
    //Monta o Evento de Cancelamento
    return Try.of(() -> EpecUtil.montaEpec(epec, config))
        //Envia Evento EPEC
        .mapTry(enviEvento -> Nfe.enviarEpec(config, enviEvento, true))
        .mapTry(retorno -> {
          //Valida o Retorno do Cancelamento
          RetornoUtil.validaEpec(retorno);

          //Resultado
          log.info("\n");
          retorno.getRetEvento().forEach(resultado -> {
            log.info("# Chave: " + resultado.getInfEvento().getChNFe());
            log.info("# Status: " + resultado.getInfEvento().getCStat() + " - " + resultado.getInfEvento().getXMotivo());
            log.info("# Protocolo: " + resultado.getInfEvento().getNProt());
          });

          //Cria ProcEvento de Cacnelamento
          //String proc = EpecUtil.criaProcEventoEpec(config, enviEvento, retorno);
          log.info("\n");
          //System.out.println("# ProcEvento : " + proc);
          return retorno;
        })
        .onFailure(t -> log.error("Failed on authorization", t));
  }
}
