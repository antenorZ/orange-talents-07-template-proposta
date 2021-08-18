package br.com.zup.propostas.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.propostas.config.validation.CPFOrCNPJ;
import br.com.zup.propostas.enums.EstadoProposta;
import br.com.zup.propostas.enums.ResultadoSolicitacao;

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
	private String documento;
	
	@NotBlank
	private String enderecoCompleto;
	
	@Positive
	@NotNull
	private Double salario;
	
	@NotNull
    @Enumerated(EnumType.STRING)
    private EstadoProposta estadoProposta;

	public Proposta() {
	
	}

	public Proposta(@NotBlank String nome, @Email @NotBlank String email){
		this.nome = nome;
		this.email = email;
	}

	public Proposta(@NotBlank String nome, @Email @NotBlank String email, @NotBlank String documento,
			@NotBlank String enderecoCompleto, @Positive @NotNull Double salario) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.enderecoCompleto = enderecoCompleto;
		this.salario = salario;
		this.estadoProposta = EstadoProposta.NAO_ELEGIVEL;
	}

	public void atualizaEstadoProposta(ResultadoSolicitacao resultadoSolicitacao) {
        this.estadoProposta = resultadoSolicitacao.getEstadoProposta();
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

	public EstadoProposta getEstadoProposta() {
		return estadoProposta;
	}
	
	
}
