package br.com.zup.propostas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.propostas.model.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	Optional<Proposta> findByDocumento(String documento);
}
