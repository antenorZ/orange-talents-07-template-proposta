package br.com.zup.propostas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zup.propostas.model.Proposta;

public class PropostaDto {
	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;
	
	public Proposta toModel() {
		return new Proposta(nome, email);
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
	
}
