package br.com.zup.propostas.repository;

import br.com.zup.propostas.model.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
    List<Bloqueio> findByestadoBloqueio(Enum<?> estadoBloqueio);
}
