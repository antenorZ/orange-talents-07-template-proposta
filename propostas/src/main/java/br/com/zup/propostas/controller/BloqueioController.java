package br.com.zup.propostas.controller;

import br.com.zup.propostas.dto.ErroDeFormularioDto;
import br.com.zup.propostas.dto.RetornaDadosBloqueioCartaoDto;
import br.com.zup.propostas.dto.RetornaDadosSolicitanteDto;
import br.com.zup.propostas.enums.EstadoBloqueio;
import br.com.zup.propostas.enums.ResultadoBloqueio;
import br.com.zup.propostas.enums.ResultadoSolicitacao;
import br.com.zup.propostas.form.BloqueioForm;
import br.com.zup.propostas.form.ConsultaDadosSolicitanteForm;
import br.com.zup.propostas.form.PropostaForm;
import br.com.zup.propostas.integrations.BloqueiaCartaoClient;
import br.com.zup.propostas.model.Bloqueio;
import br.com.zup.propostas.model.Cartao;
import br.com.zup.propostas.model.Proposta;
import br.com.zup.propostas.repository.BloqueioRepository;
import br.com.zup.propostas.repository.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueio/{id}")
public class BloqueioController{

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private BloqueiaCartaoClient bloqueiaCartaoApi;

    @PostMapping
    public ResponseEntity<?> bloqueiaCartao(@PathVariable String id, @RequestHeader("X-Forward-For") String xForwardFor, @RequestBody @Valid BloqueioForm bloqueioForm, UriComponentsBuilder uriBuilder){
        Optional<Cartao> possivelCartao = cartaoRepository.findBynumeroCartao(id);
        if(!possivelCartao.isPresent()) {
            ErroDeFormularioDto erro = new ErroDeFormularioDto("id", "Nenhum cartao foi encotrado com este identificador");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
        Cartao possivelCartaoBloqueado = possivelCartao.get();
        String ipCliente = xForwardFor.split(",")[0];
        Bloqueio possivelBloqueioRealizado = bloqueioForm.toModel(possivelCartaoBloqueado, ipCliente);
        try {
            RetornaDadosBloqueioCartaoDto retornaDadosBloqueioCartaoDto = bloqueiaCartaoApi.bloqueiaCartao(id, bloqueioForm);
            possivelBloqueioRealizado.atualizaEstadoBloqueio(retornaDadosBloqueioCartaoDto.getResultadoBloqueio());
        }catch(FeignException.FeignClientException.UnprocessableEntity e){
            possivelBloqueioRealizado.atualizaEstadoBloqueio(ResultadoBloqueio.FALHA);
        }
        Bloqueio bloqueioRealizado = bloqueioForm.toModel(possivelCartaoBloqueado, ipCliente);
        transactionTemplate.execute(status -> bloqueioRepository.save(bloqueioRealizado));
        URI uri = uriBuilder.path("/bloqueio/{id}").buildAndExpand(possivelCartaoBloqueado.getId()).toUri();
        return ResponseEntity.ok().build();
    }
}
