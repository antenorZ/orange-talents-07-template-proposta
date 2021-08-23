package br.com.zup.propostas.repository;

import java.util.List;
import java.util.Optional;

import br.com.zup.propostas.enums.EstadoProposta;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.propostas.model.Proposta;
import org.springframework.data.jpa.repository.Query;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	Optional<Proposta> findByDocumento(String documento);

	List<Proposta> findFirst5ByEstadoPropostaAndCartaoRelacionado(Enum<EstadoProposta> estadoProposta, Long idCartao);

	@Query("SELECT p FROM Proposta p WHERE p.estadoProposta = 'ELEGIVEL' and p.cartaoRelacionado = NULL")
	List<Proposta> findEligibleProposesWithoutCard();
}
