package br.com.zup.propostas.form;

import javax.validation.constraints.NotBlank;

import br.com.zup.propostas.config.validation.CPFOrCNPJ;
import br.com.zup.propostas.model.Proposta;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultaDadosSolicitanteForm {
    @CPFOrCNPJ
    @NotBlank
	@JsonProperty("documento")
    private String documentoSolicitante;

    @NotBlank
	@JsonProperty("nome")
	private String nomeSolicitante;

    @NotBlank
	@JsonProperty
	private String idProposta;



	public ConsultaDadosSolicitanteForm(Proposta proposta) {
		this.documentoSolicitante = proposta.getDocumento();
		this.nomeSolicitante = proposta.getNome();
		this.idProposta = proposta.getId().toString();
	}

	public ConsultaDadosSolicitanteForm(String idProposta, String documento, String nome) {
		this.nomeSolicitante = nome;
		this.documentoSolicitante = documento;
		this.idProposta = idProposta;
	}

	public String getDocumentoSolicitante() {
		return documentoSolicitante;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public String getIdProposta() {
		return idProposta;
	}
}
