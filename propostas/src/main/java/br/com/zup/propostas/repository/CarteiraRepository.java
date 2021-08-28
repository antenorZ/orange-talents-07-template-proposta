package br.com.zup.propostas.repository;

import br.com.zup.propostas.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
