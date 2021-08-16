package br.com.zup.propostas.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.propostas.model.Solicitante;
import br.com.zup.propostas.validation.CPFOrCNPJ;

public class SolicitanteForm {
	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;
	
	@CPFOrCNPJ
	@NotBlank
	private String documento;
	
	@NotBlank
	private String enderecoCompleto;
	
	@Positive
	@NotNull
	private Double salario;

	public SolicitanteForm() {
		
	}

	public SolicitanteForm(@NotBlank String nome, @Email @NotBlank String email, @NotBlank String documento,
			@NotBlank String enderecoCompleto, @Positive @NotNull Double salario) {
		super();
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.enderecoCompleto = enderecoCompleto;
		this.salario = salario;
	}

	public Solicitante toModel() {
		return new Solicitante(nome, email, documento, enderecoCompleto, salario);
	}
	
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public Double getSalario() {
		return salario;
	}
	
	
	
}
