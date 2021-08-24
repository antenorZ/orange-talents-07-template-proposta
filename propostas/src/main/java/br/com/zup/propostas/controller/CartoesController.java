package br.com.zup.propostas.controller;

import br.com.zup.propostas.dto.ErroDeFormularioDto;
import br.com.zup.propostas.dto.RetornaDadosSolicitanteDto;
import br.com.zup.propostas.enums.ResultadoSolicitacao;
import br.com.zup.propostas.form.BiometriaForm;
import br.com.zup.propostas.form.ConsultaDadosSolicitanteForm;
import br.com.zup.propostas.form.PropostaForm;
import br.com.zup.propostas.model.Biometria;
import br.com.zup.propostas.model.Cartao;
import br.com.zup.propostas.model.Proposta;
import br.com.zup.propostas.repository.BiometriaRepository;
import br.com.zup.propostas.repository.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class CartoesController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PostMapping("/{numeroCartao}/biometria")
    public ResponseEntity criaBiometria(@PathVariable("numeroCartao") String numeroCartao, @RequestBody @Valid BiometriaForm biometriaForm, UriComponentsBuilder uriBuilder) {
        Optional<Cartao> cartao = cartaoRepository.findBynumeroCartao(numeroCartao);
        if(cartao.isPresent()){
            Biometria biometria = biometriaForm.toModel(cartao.get());
            transactionTemplate.execute(status-> biometriaRepository.save(biometria));
//            URI uri = uriBuilder.path("/{numeroCartao}/biometria").buildAndExpand(cartao.get().getId()).toUri();
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
