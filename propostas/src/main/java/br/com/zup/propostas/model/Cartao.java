package br.com.zup.propostas.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    private LocalDateTime dataEmissao;

    private String titular;

    @OneToMany(mappedBy = "cartaoRelacionado", cascade = {MERGE, PERSIST, REMOVE})
    private List<Bloqueio> bloqueios = new ArrayList<>();

    public Cartao() {
    }

    public Cartao(String numeroCartao, LocalDateTime dataEmissao, String titular) {
        this.numeroCartao = numeroCartao;
        this.dataEmissao = dataEmissao;
        this.titular = titular;
    }

    public Long getId() {
        return id;
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

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    @OneToMany(mappedBy = "cartaoRelacionado")
    private List<Aviso> avisos = new ArrayList<>();

    @OneToMany(mappedBy = "cartaoRelacionado")
    private List<Carteira> carteiras = new ArrayList<>();

    @OneToMany(mappedBy = "cartaoRelacionado")
    private List<Parcela> parcelas = new ArrayList<>();

    private Integer limite;

    @OneToMany(mappedBy = "cartaoRelacionado")
    private List<Renegociacao> renegociacoes = new ArrayList<>();

    @OneToOne
    private Vencimento vencimento;


}
