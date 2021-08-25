package br.com.zup.propostas.dto;

import br.com.zup.propostas.enums.ResultadoBloqueio;

public class RetornaDadosBloqueioCartaoDto {
    private ResultadoBloqueio resultadoBloqueio;

    public ResultadoBloqueio getResultadoBloqueio() {
        return resultadoBloqueio;
    }

    public RetornaDadosBloqueioCartaoDto(ResultadoBloqueio resultadoBloqueio) {
        this.resultadoBloqueio = resultadoBloqueio;
    }
}
