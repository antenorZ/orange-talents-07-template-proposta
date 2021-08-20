package br.com.zup.propostas.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate validade;

    private String destino;

    @ManyToOne
    private Cartao cartaoRelacionado;

    public Aviso(LocalDate validade, String destino, Cartao cartaoRelacionado) {
        this.validade = validade;
        this.destino = destino;
        this.cartaoRelacionado = cartaoRelacionado;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public String getDestino() {
        return destino;
    }

    public Cartao getCartaoRelacionado() {
        return cartaoRelacionado;
    }
}
