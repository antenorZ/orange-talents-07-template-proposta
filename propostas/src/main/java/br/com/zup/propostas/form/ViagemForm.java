package br.com.zup.propostas.form;

import br.com.zup.propostas.model.Cartao;
import br.com.zup.propostas.model.Viagem;

import java.time.LocalDate;

public class ViagemForm {
    private String destino;

    private LocalDate validoAte;

    public ViagemForm() {
    }

    public ViagemForm(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public Viagem toModel(Cartao cartao, String ipCliente, String userAgent){
        return new Viagem(destino, validoAte, ipCliente, userAgent, cartao);
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
