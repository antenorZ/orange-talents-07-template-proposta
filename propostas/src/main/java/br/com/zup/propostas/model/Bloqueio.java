package br.com.zup.propostas.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idApi;

    private LocalDateTime dataBloqueio;

    private String sistemaResponsavel;

    private Boolean ativo;

    @ManyToOne
    private Cartao cartaoRelacionado;

    public Bloqueio(String idApi, LocalDateTime dataBloqueio, String sistemaResponsavel, Boolean ativo, Cartao cartaoRelacionado) {
        this.idApi = idApi;
        this.dataBloqueio = dataBloqueio;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
        this.cartaoRelacionado = cartaoRelacionado;
    }

    public Long getId() {
        return id;
    }

    public String getIdApi() {
        return idApi;
    }

    public LocalDateTime getDataBloqueio() {
        return dataBloqueio;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Cartao getCartaoRelacionado() {
        return cartaoRelacionado;
    }
}
