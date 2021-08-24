package br.com.zup.propostas.form;

import br.com.zup.propostas.model.Biometria;
import br.com.zup.propostas.model.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class BiometriaForm {

    private String fingerprint;

    public Biometria toModel(Cartao cartao){
        return new Biometria(cartao, fingerprint);
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
