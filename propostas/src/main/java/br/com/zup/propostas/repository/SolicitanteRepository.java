package br.com.zup.propostas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.propostas.model.Solicitante;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Long>{
	Optional<Solicitante> findByDocumento(String documento);
}
