package br.com.zup.propostas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zup.propostas.model.Solicitante;

public class SolicitanteDto {
	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;
	
	public Solicitante toModel() {
		return new Solicitante(nome, email);
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
	
}
