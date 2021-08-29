package br.com.zup.propostas.dto;

import br.com.zup.propostas.enums.EstadoCarteira;
import br.com.zup.propostas.enums.ResultadoCarteira;
import br.com.zup.propostas.enums.ResultadoViagem;

public class RetornaDadosCarteiraDto {

    private ResultadoCarteira resultadoCarteira;

    private String id;

    public RetornaDadosCarteiraDto(ResultadoCarteira resultadoCarteira, String id) {
        this.resultadoCarteira = resultadoCarteira;
        this.id = id;
    }

    public ResultadoCarteira getResultadoCarteira() {
        return resultadoCarteira;
    }

    public String getId() {
        return id;
    }

}
