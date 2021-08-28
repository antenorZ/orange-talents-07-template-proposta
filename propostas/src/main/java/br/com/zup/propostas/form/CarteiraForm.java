package br.com.zup.propostas.form;

import br.com.zup.propostas.model.Cartao;
import br.com.zup.propostas.model.Carteira;

public class CarteiraForm {
    private String nome;

    private String email;

    private String carteira;

    public Carteira toModel(Cartao cartao){
        return new Carteira(nome, email, carteira, cartao);
    }

    public CarteiraForm(String nome, String email, String carteira) {
        this.nome = nome;
        this.email = email;
        this.carteira = carteira;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
