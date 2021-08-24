package br.com.zup.propostas.form;

import br.com.zup.propostas.model.Biometria;
import br.com.zup.propostas.model.Cartao;

import javax.validation.constraints.NotNull;

public class BiometriaForm {

    private String idCartao;

    @NotNull
    private String polegar;

    public BiometriaForm(String polegar) {
        this.polegar = polegar;
    }

    public Biometria toModel(Cartao cartao){
        return new Biometria(cartao, polegar);
    }

    public String getPolegar() {
        return polegar;
    }
}
