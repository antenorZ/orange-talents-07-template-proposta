package br.com.zup.propostas.integrations;

import br.com.zup.propostas.dto.RetornaDadosBloqueioCartaoDto;
import br.com.zup.propostas.dto.RetornaDadosViagemDto;
import br.com.zup.propostas.form.ViagemForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "agendaViagem", url = "${contas.host}")
public interface AgendaViagemClient{
    @PostMapping("/api/cartoes/{id}/avisos")
    RetornaDadosViagemDto agendaViagem(@PathVariable String id, @RequestBody ViagemForm viagemForm);
}
