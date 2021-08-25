package br.com.zup.propostas.integrations;

import br.com.zup.propostas.dto.RetornaDadosBloqueioCartaoDto;
import br.com.zup.propostas.form.BloqueioForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bloqueiaCartao", url = "${contas.host}")
public interface BloqueiaCartaoClient {
    @PostMapping("/api/cartoes/{id}/bloqueios")
    RetornaDadosBloqueioCartaoDto bloqueiaCartao(@PathVariable String id, @RequestBody BloqueioForm bloqueioForm);
}
