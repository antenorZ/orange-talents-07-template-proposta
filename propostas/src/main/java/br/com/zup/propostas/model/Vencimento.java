package br.com.zup.propostas.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vencimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idApi;

    private Integer dia;

    private LocalDateTime dataCriacao;

    @ManyToOne
    private Cartao cartaoRelacionado;
}
