package br.com.zup.propostas.form;

import br.com.zup.propostas.model.Cartao;
import br.com.zup.propostas.model.Carteira;

public class CarteiraForm {
    private String email;

    private String carteira;

    public Carteira toModel(Cartao cartao){
        return new Carteira(carteira, email, cartao);
    }

    public CarteiraForm(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
