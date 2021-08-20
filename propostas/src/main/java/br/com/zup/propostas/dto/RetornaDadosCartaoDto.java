package br.com.zup.propostas.dto;

import br.com.zup.propostas.model.Cartao;

import java.time.LocalDateTime;

public class RetornaDadosCartaoDto {

    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    public Cartao toModel(){
        return new Cartao(id, emitidoEm, titular);
    }

    public RetornaDadosCartaoDto(String id, LocalDateTime emitidoEm, String titular) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }
}
