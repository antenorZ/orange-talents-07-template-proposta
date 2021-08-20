package br.com.zup.propostas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Parcela {

    @Id
    @GeneratedValue
    private Long id;

    private String idApi;

    private Integer quantidade;

    private Integer valor;

    @ManyToOne
    private Cartao cartaoRelacionado;
}
