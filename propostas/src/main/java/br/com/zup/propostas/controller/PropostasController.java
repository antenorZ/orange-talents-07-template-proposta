package br.com.zup.propostas.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.zup.propostas.dto.RetornaDadosCartaoDto;
import br.com.zup.propostas.enums.EstadoProposta;
import br.com.zup.propostas.integrations.AssociaCartaoPropostaClient;
import br.com.zup.propostas.integrations.ConsultaDadosSolicitanteClient;
import br.com.zup.propostas.model.Cartao;
import br.com.zup.propostas.repository.CartaoRepository;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.propostas.dto.ErroDeFormularioDto;
import br.com.zup.propostas.dto.PropostaDto;
import br.com.zup.propostas.dto.RetornaDadosSolicitanteDto;
import br.com.zup.propostas.enums.ResultadoSolicitacao;
import br.com.zup.propostas.form.ConsultaDadosSolicitanteForm;
import br.com.zup.propostas.form.PropostaForm;
//import br.com.zup.propostas.integrations.ConsultaDadosSolicitanteApi;
import br.com.zup.propostas.model.Proposta;
import br.com.zup.propostas.repository.PropostaRepository;
import feign.FeignException;


@RestController
@RequestMapping("/propostas")
public class PropostasController{
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private ConsultaDadosSolicitanteClient consultaDadosApi;

	@Autowired
	private AssociaCartaoPropostaClient associaCartaoApi;

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity criaNovaProposta(@RequestBody @Valid PropostaForm propostaForm, UriComponentsBuilder uriBuilder){
		Optional<Proposta> possivelSolicitante = propostaRepository.findByDocumento(propostaForm.getDocumento());
		if(possivelSolicitante.isPresent()) {
			ErroDeFormularioDto erro = new ErroDeFormularioDto("documento", "já existe uma proposta cadastrada para esse documento");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
		}
		Proposta proposta = propostaForm.toModel();
		propostaRepository.save(proposta);
		
		try {
			ConsultaDadosSolicitanteForm consultaDados = new ConsultaDadosSolicitanteForm(proposta);
			RetornaDadosSolicitanteDto retornaDadosSolicitante = consultaDadosApi.consultaDadosSolicitanteApi(consultaDados);
			proposta.atualizaEstadoProposta(retornaDadosSolicitante.getResultadoSolicitacao());
			RetornaDadosCartaoDto retornaDadosCartao = associaCartaoApi.criaCartao(consultaDados);
			Cartao novoCartaoRetornado = retornaDadosCartao.toModel();
			cartaoRepository.save(novoCartaoRetornado);
		}catch(FeignException e){
			proposta.atualizaEstadoProposta(ResultadoSolicitacao.COM_RESTRICAO);
		}
		propostaRepository.save(proposta);

		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity verDetalhesProposta(@PathVariable("id") Long id){
		Optional<Proposta> proposta = propostaRepository.findById(id);
		if(proposta.isPresent()) {
			return ResponseEntity.ok(new PropostaDto(proposta.get()));
		}
		return ResponseEntity.notFound().build();
	}
}
