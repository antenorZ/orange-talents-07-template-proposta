package br.com.zup.propostas.dto;

import br.com.zup.propostas.enums.ResultadoViagem;

public class RetornaDadosViagemDto {
    ResultadoViagem resultadoViagem;

    public ResultadoViagem getResultadoViagem() {
        return resultadoViagem;
    }

    public RetornaDadosViagemDto(ResultadoViagem resultadoViagem) {
        this.resultadoViagem = resultadoViagem;
    }
}
