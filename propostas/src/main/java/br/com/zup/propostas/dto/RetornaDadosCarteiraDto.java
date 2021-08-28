package br.com.zup.propostas.dto;

import br.com.zup.propostas.enums.EstadoCarteira;

public class RetornaDadosCarteiraDto {

    private EstadoCarteira estadoCarteira;

    private String id;

    public RetornaDadosCarteiraDto(EstadoCarteira estadoCarteira, String id) {
        this.estadoCarteira = estadoCarteira;
        this.id = id;
    }

    public EstadoCarteira getEstadoCarteira() {
        return estadoCarteira;
    }

    public String getId() {
        return id;
    }
}
