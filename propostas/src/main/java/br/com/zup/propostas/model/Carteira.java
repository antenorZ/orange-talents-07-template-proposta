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

    private String idApi;

    private String email;

    private LocalDateTime dataAssociacao;

    private String emissor;

    @ManyToOne
    private Cartao cartaoRelacionado;

}
