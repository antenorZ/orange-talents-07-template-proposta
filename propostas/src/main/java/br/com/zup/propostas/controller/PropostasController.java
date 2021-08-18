package br.com.zup.propostas.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.propostas.dto.PropostaDto;
import br.com.zup.propostas.form.PropostaForm;
import br.com.zup.propostas.model.Proposta;
import br.com.zup.propostas.repository.PropostaRepository;

import br.com.zup.propostas.dto.ErroDeFormularioDto;


@RestController
@RequestMapping("/propostas")
public class PropostasController{
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@PostMapping
	public ResponseEntity criaNovaProposta(@RequestBody @Valid PropostaForm propostaForm, UriComponentsBuilder uriBuilder){
		Optional<Proposta> possivelSolicitante = propostaRepository.findByDocumento(propostaForm.getDocumento());
		if(possivelSolicitante.isPresent()) {
			ErroDeFormularioDto erro = new ErroDeFormularioDto("documento", "já existe uma proposta cadastrada para esse documento");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
		}
		Proposta proposta = propostaForm.toModel();
		propostaRepository.save(proposta);
		URI uri = uriBuilder.path("/solicitantes/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(uri);
	}
}
