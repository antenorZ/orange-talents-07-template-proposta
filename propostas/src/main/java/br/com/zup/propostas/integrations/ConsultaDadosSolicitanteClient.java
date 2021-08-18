package br.com.zup.propostas.integrations;

import br.com.zup.propostas.config.FeignConfig;
import br.com.zup.propostas.dto.RetornaDadosSolicitanteDto;
import br.com.zup.propostas.form.ConsultaDadosSolicitanteForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= "solicitacao", url = "${analise-financeira.host}", configuration = FeignConfig.class)
public interface ConsultaDadosSolicitanteClient {
    @PostMapping("${analise-financeira.analisa-proposta}")
    RetornaDadosSolicitanteDto consultaDadosSolicitanteApi(@RequestBody ConsultaDadosSolicitanteForm consultaDadosSolicitanteForm);
}
