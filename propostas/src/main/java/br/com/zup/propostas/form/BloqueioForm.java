package br.com.zup.propostas.form;

import br.com.zup.propostas.model.Bloqueio;
import br.com.zup.propostas.model.Cartao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

public class BloqueioForm {
    @NotBlank
    private String sistemaResponsavel;

    public Bloqueio toModel(Cartao cartao, String ipCliente){
        return new Bloqueio(sistemaResponsavel, cartao, ipCliente);
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
