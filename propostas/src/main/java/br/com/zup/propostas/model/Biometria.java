package br.com.zup.propostas.model;

import org.bouncycastle.util.encoders.Base64Encoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cartao cartaoRelacionado;

    private LocalDateTime momentoCadastro;

    @NotNull
    private String fingerprint;

    public Biometria(Cartao cartaoRelacionado, String fingerprint) {
        this.cartaoRelacionado = cartaoRelacionado;
        this.momentoCadastro = LocalDateTime.now();
        this.fingerprint = Biometria.encode(fingerprint);
    }

    public static String encode(String fingerprint){
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(fingerprint.getBytes(StandardCharsets.UTF_8));
        return new String(encoded);
    }

    public Cartao getCartaoRelacionado() {
        return cartaoRelacionado;
    }

    public LocalDateTime getMomentoCadastro() {
        return momentoCadastro;
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
