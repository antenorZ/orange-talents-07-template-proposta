package br.com.zup.propostas.integrations;

import br.com.zup.propostas.dto.RetornaDadosCartaoDto;
import br.com.zup.propostas.dto.RetornaDadosSolicitanteDto;
import br.com.zup.propostas.form.ConsultaDadosSolicitanteForm;
import br.com.zup.propostas.model.Proposta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "associaCartaoProposta", url = "${contas.host}")
public interface AssociaCartaoPropostaClient {

    @GetMapping("${contas.associaCartao}")
    RetornaDadosCartaoDto associaCartao(@RequestParam String idProposta);
}
