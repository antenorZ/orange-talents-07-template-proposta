package br.com.zup.propostas.integrations;

import br.com.zup.propostas.dto.RetornaDadosCarteiraDto;
import br.com.zup.propostas.form.BloqueioForm;
import br.com.zup.propostas.form.CarteiraForm;
import br.com.zup.propostas.model.Carteira;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "associaCartaoCarteira", url = "${contas.host}")
public interface AssociaCarteiraClient {
    @PostMapping("/api/cartoes/{id}/carteiras")
    RetornaDadosCarteiraDto associaCarteira(@PathVariable String id, @RequestBody CarteiraForm carteiraForm);
}
