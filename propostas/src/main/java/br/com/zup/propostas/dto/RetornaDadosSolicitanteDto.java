package br.com.zup.propostas.dto;

import br.com.zup.propostas.enums.ResultadoSolicitacao;
import br.com.zup.propostas.model.Proposta;

public class RetornaDadosSolicitanteDto {
	private String documento;
	private String nome;
	private ResultadoSolicitacao resultadoSolicitacao;
	private String idProposta;

	public RetornaDadosSolicitanteDto(){

	}

	public RetornaDadosSolicitanteDto(String documento, String nome, ResultadoSolicitacao resultadoSolicitacao,
									  String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
		this.idProposta = idProposta;
	}
	
	public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}


	@Override
	public String toString() {
		return "RetornaDadosSolicitanteDto{" +
				"documento='" + documento + '\'' +
				", nome='" + nome + '\'' +
				", resultadoSolicitacao=" + resultadoSolicitacao +
				", idProposta='" + idProposta + '\'' +
				'}';
	}
}
