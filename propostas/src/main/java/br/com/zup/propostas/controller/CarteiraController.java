package br.com.zup.propostas.controller;

import br.com.zup.propostas.dto.ErroDeFormularioDto;
import br.com.zup.propostas.dto.RetornaDadosCarteiraDto;
import br.com.zup.propostas.dto.RetornaDadosViagemDto;
import br.com.zup.propostas.enums.ResultadoViagem;
import br.com.zup.propostas.form.CarteiraForm;
import br.com.zup.propostas.form.ViagemForm;
import br.com.zup.propostas.integrations.AgendaViagemClient;
import br.com.zup.propostas.integrations.AssociaCarteiraClient;
import br.com.zup.propostas.model.Cartao;
import br.com.zup.propostas.model.Carteira;
import br.com.zup.propostas.model.Viagem;
import br.com.zup.propostas.repository.CartaoRepository;
import br.com.zup.propostas.repository.CarteiraRepository;
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
public class CarteiraController {
    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private AssociaCarteiraClient associaCarteiraClient;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PostMapping("/{id}/carteiras")
    public ResponseEntity<?> criaAssociacaoCarteira(@PathVariable("id") String id, @RequestHeader("User-Agent") String userAgent, @RequestHeader("X-Forward-For") String xForwardFor, @RequestBody @Valid CarteiraForm carteiraForm, UriComponentsBuilder uriBuilder){
        Optional<Cartao> cartao = cartaoRepository.findBynumeroCartao(id);
        if(!cartao.isPresent()){
            ErroDeFormularioDto erro = new ErroDeFormularioDto("id", "Nenhum cartao foi encotrado com este identificador");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
        Cartao cartaoPresente = cartao.get();
        Carteira carteira = carteiraForm.toModel(cartaoPresente);
        try{
            RetornaDadosCarteiraDto dadosCarteira = associaCarteiraClient.associaCarteira(id, carteiraForm);
            carteira.atualizaEstadoCarteira(dadosCarteira.getResultadoCarteira());
        } catch(FeignException.FeignClientException.InternalServerError e){
            ErroDeFormularioDto erro = new ErroDeFormularioDto("idCartao", "Um erro de negocio foi identificado");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
        }
        Carteira carteiraParaSalvar = carteiraForm.toModel(cartaoPresente);
        transactionTemplate.execute(status -> carteiraRepository.save(carteiraParaSalvar));
        URI uri = uriBuilder.path("/carteiras/{id}").buildAndExpand(carteiraParaSalvar.getId()).toUri();
        return ResponseEntity.ok().build();
    }
}
