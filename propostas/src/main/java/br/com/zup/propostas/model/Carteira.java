package br.com.zup.propostas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Carteira {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String email;

    private LocalDateTime dataAssociacao;

    private String emissor;

    @ManyToOne
    private Cartao cartaoRelacionado;

    public Carteira(String nome, String email, String emissor, Cartao cartaoRelacionado) {
        this.nome = nome;
        this.email = email;
        this.dataAssociacao = LocalDateTime.now();
        this.emissor = emissor;
        this.cartaoRelacionado = cartaoRelacionado;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDataAssociacao() {
        return dataAssociacao;
    }

    public String getEmissor() {
        return emissor;
    }

    public Cartao getCartaoRelacionado() {
        return cartaoRelacionado;
    }

    public String getNome() {
        return nome;
    }


}
