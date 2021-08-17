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

import br.com.zup.propostas.dto.SolicitanteDto;
import br.com.zup.propostas.form.SolicitanteForm;
import br.com.zup.propostas.model.Solicitante;
import br.com.zup.propostas.repository.SolicitanteRepository;

import br.com.zup.propostas.dto.ErroDeFormularioDto;


@RestController
@RequestMapping("/solicitantes")
public class SolicitantesController{
	
	@Autowired
	private SolicitanteRepository solicitanteRepository;
	
	@PostMapping
	public ResponseEntity criar(@RequestBody @Valid SolicitanteForm solicitanteForm, UriComponentsBuilder uriBuilder){
		Optional<Solicitante> possivelSolicitante = solicitanteRepository.findByDocumento(solicitanteForm.getDocumento());
		if(possivelSolicitante.isPresent()) {
			ErroDeFormularioDto erro = new ErroDeFormularioDto("documento", "já existe uma proposta cadastrada para esse documento");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
		}
		Solicitante solicitante = solicitanteForm.toModel();
		solicitanteRepository.save(solicitante);
		URI uri = uriBuilder.path("/solicitantes/{id}").buildAndExpand(solicitante.getId()).toUri();
		return ResponseEntity.created(uri).body(uri);
	}
}
