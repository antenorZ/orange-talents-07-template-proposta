package br.com.zup.propostas.form;

import br.com.zup.propostas.model.Bloqueio;
import br.com.zup.propostas.model.Cartao;

import javax.servlet.http.HttpServletRequest;

public class BloqueioForm {
    private String sistemaResponsavel;

    private String ipCliente;

    public Bloqueio toModel(Cartao cartao){
        return new Bloqueio(sistemaResponsavel, cartao);
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
