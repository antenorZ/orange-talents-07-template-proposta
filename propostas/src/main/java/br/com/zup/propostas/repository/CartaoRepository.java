package br.com.zup.propostas.repository;

import br.com.zup.propostas.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
