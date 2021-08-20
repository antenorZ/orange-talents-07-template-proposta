package br.com.zup.propostas.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idApi;

    private Integer quantidade;

    private Integer valor;

    private LocalDateTime dataDeCriacao;

    @ManyToOne
    private Cartao cartaoRelacionado;
}
