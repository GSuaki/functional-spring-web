package com.suaki.functionalspring.functions.epec;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import io.vavr.Function0;
import io.vavr.control.Try;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConfigurateEpec implements Function0<Try<ConfiguracoesNfe>> {

  @Override
  public Try<ConfiguracoesNfe> apply() {
    final ClassLoader loader = ConfigurateEpec.class.getClassLoader();

    return Try.of(() -> {
      final URI uri = Objects.requireNonNull(loader.getResource("CertificadoTesteCNPJ.pfx")).toURI();

      final Certificado certificado = CertificadoService.certificadoPfx(Paths.get(uri).toString(), "123456");

      final String path = Objects.requireNonNull(loader.getResource("schemas/envEPEC_v1.00.xsd"))
          .getPath()
          .replace("envEPEC_v1.00.xsd", "");

      log.info(path);
      return ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.SP, AmbienteEnum.HOMOLOGACAO, certificado, path);
    });
  }
}
