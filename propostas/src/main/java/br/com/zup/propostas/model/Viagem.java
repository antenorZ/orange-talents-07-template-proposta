package br.com.zup.propostas.model;

import br.com.zup.propostas.enums.EstadoViagem;
import br.com.zup.propostas.enums.ResultadoViagem;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;

    private LocalDate dataFimViagem;

    private LocalDateTime momentoRegistro;

    private String ipCliente;

    private String userAgent;

    @ManyToOne
    private Cartao cartaoRelacionado;

    private EstadoViagem estadoViagem;

    public Viagem() {
    }

    public Viagem(String destino, LocalDate dataFimViagem, String ipCliente, String userAgent, Cartao cartaoRelacionado) {
        this.destino = destino;
        this.dataFimViagem = dataFimViagem;
        this.momentoRegistro = LocalDateTime.now();
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartaoRelacionado = cartaoRelacionado;
    }

    public void atualizaEstadoViagem(ResultadoViagem resultadoViagem){
        this.estadoViagem = resultadoViagem.getEstadoViagem();
    }

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataFimViagem() {
        return dataFimViagem;
    }

    public LocalDateTime getMomentoRegistro() {
        return momentoRegistro;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartaoRelacionado() {
        return cartaoRelacionado;
    }

    public EstadoViagem getEstadoViagem() {
        return estadoViagem;
    }
}
