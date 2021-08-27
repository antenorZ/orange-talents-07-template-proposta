package br.com.zup.propostas.controller;

import br.com.zup.propostas.dto.ErroDeFormularioDto;
import br.com.zup.propostas.dto.RetornaDadosViagemDto;
import br.com.zup.propostas.enums.ResultadoViagem;
import br.com.zup.propostas.form.BiometriaForm;
import br.com.zup.propostas.form.ViagemForm;
import br.com.zup.propostas.integrations.AgendaViagemClient;
import br.com.zup.propostas.model.Biometria;
import br.com.zup.propostas.model.Bloqueio;
import br.com.zup.propostas.model.Cartao;
import br.com.zup.propostas.model.Viagem;
import br.com.zup.propostas.repository.CartaoRepository;
import br.com.zup.propostas.repository.ViagemRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private AgendaViagemClient agendaViagemClient;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public ResponseEntity<?> criaAvisoViagem(@PathVariable("numeroCartao") String id,  @RequestHeader("User-Agent") String userAgent, @RequestHeader("X-Forward-For") String xForwardFor, @RequestBody @Valid ViagemForm viagemForm, UriComponentsBuilder uriBuilder){
        Optional<Cartao> cartao = cartaoRepository.findBynumeroCartao(id);
        if(!cartao.isPresent()){
            ErroDeFormularioDto erro = new ErroDeFormularioDto("id", "Nenhum cartao foi encotrado com este identificador");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
        Cartao cartaoPresente = cartao.get();
        String ipCliente = xForwardFor.split(",")[0];
        Viagem possivelAvisoViagem = viagemForm.toModel(cartaoPresente, ipCliente, userAgent);
        try{
            RetornaDadosViagemDto retornaDadosViagem = agendaViagemClient.agendaViagem(id, viagemForm);
            possivelAvisoViagem.atualizaEstadoViagem(retornaDadosViagem.getResultadoViagem());
        } catch(FeignException.FeignClientException.InternalServerError e){
            possivelAvisoViagem.atualizaEstadoViagem(ResultadoViagem.FALHA);
        }
        Viagem avisoViagem = viagemForm.toModel(cartaoPresente, ipCliente, userAgent);
        transactionTemplate.execute(status -> viagemRepository.save(avisoViagem));
        URI uri = uriBuilder.path("/viagens/{id}").buildAndExpand(avisoViagem.getId()).toUri();
        return ResponseEntity.ok().build();
    }
}
