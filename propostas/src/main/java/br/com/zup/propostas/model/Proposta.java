package br.com.zup.propostas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.propostas.config.validation.CPFOrCNPJ;

@Entity
public class Proposta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;
	
	@CPFOrCNPJ
	@NotBlank
//	@Unique(domainClass = this.class, fieldName = "documento")
	private String documento;
	
	@NotBlank
	private String enderecoCompleto;
	
	@Positive
	@NotNull
	private Double salario;

	public Proposta() {
	
	}

	public Proposta(@NotBlank String nome, @Email @NotBlank String email){
		this.nome = nome;
		this.email = email;
	}

	public Proposta(@NotBlank String nome, @Email @NotBlank String email, @NotBlank String documento,
			@NotBlank String enderecoCompleto, @Positive @NotNull Double salario) {
		super();
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.enderecoCompleto = enderecoCompleto;
		this.salario = salario;
	}

	public Long getId() {
		return id;
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
