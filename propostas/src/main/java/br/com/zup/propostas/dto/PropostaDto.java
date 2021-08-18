package br.com.zup.propostas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zup.propostas.model.Proposta;

public class PropostaDto {
	@NotBlank
	private String nome;

	private String resultadoProposta;

	public PropostaDto(Proposta proposta) {
		this.nome = proposta.getNome();
		this.resultadoProposta = proposta.getEstadoProposta().toString();
	}

	public String getNome() {
		return nome;
	}

	public String getResultadoProposta() {
		return resultadoProposta;
	}
}
