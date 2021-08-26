package br.com.zup.propostas.dto;

import br.com.zup.propostas.model.Cartao;
import br.com.zup.propostas.model.Proposta;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CartaoDto {

    private String numeroCartao;

    private LocalDateTime dataEmissao;

    private String titular;

    public CartaoDto(Cartao cartao) {
        this.numeroCartao = cartao.getNumeroCartao();
        this.dataEmissao = cartao.getDataEmissao();
        this.titular = cartao.getTitular();
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public String getTitular() {
        return titular;
    }
}
