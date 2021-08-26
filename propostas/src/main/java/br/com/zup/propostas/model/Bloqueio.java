package br.com.zup.propostas.model;

import br.com.zup.propostas.enums.EstadoBloqueio;
import br.com.zup.propostas.enums.ResultadoBloqueio;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataBloqueio;

    private String ipCliente;

    private String sistemaResponsavel;

    @ManyToOne
    private Cartao cartaoRelacionado;

    @Enumerated(EnumType.STRING)
    private EstadoBloqueio estadoBloqueio;

    public Bloqueio(String sistemaResponsavel, Cartao cartaoRelacionado, String ipCliente) {
        this.dataBloqueio = LocalDateTime.now();
        this.ipCliente = ipCliente;
        this.sistemaResponsavel = sistemaResponsavel;
        this.cartaoRelacionado = cartaoRelacionado;
        this.estadoBloqueio = estadoBloqueio;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataBloqueio() {
        return dataBloqueio;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Cartao getCartaoRelacionado() {
        return cartaoRelacionado;
    }
    public EstadoBloqueio getEstadoBloqueio() {
        return estadoBloqueio;
    }

    public void atualizaEstadoBloqueio(ResultadoBloqueio resultadoBloqueio){
        this.estadoBloqueio = resultadoBloqueio.getEstadoBloqueio();
    }
}
