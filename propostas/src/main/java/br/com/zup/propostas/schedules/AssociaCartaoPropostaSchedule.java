package br.com.zup.propostas.schedules;

import br.com.zup.propostas.dto.RetornaDadosCartaoDto;
import br.com.zup.propostas.enums.EstadoProposta;
import br.com.zup.propostas.integrations.AssociaCartaoPropostaClient;
import br.com.zup.propostas.model.Cartao;
import br.com.zup.propostas.model.Proposta;
import br.com.zup.propostas.repository.CartaoRepository;
import br.com.zup.propostas.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class AssociaCartaoPropostaSchedule {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AssociaCartaoPropostaClient associaCartaoApi;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Scheduled(fixedRate = 5000)
    public void associaCartaoPeriodicamente(){
        List<Proposta> propostasSemCartao = propostaRepository.findFirst5ByEstadoPropostaAndCartaoRelacionado(EstadoProposta.ELEGIVEL, null);
        for (Proposta propostaSemCartao: propostasSemCartao){
            try {
                RetornaDadosCartaoDto retornaDadosCartao = associaCartaoApi.associaCartao(propostaSemCartao.getId().toString());
                Cartao cartaoRetornado = retornaDadosCartao.toModel();
                propostaSemCartao.associaCartao(cartaoRetornado);
                transactionTemplate.execute(status -> propostaRepository.save(propostaSemCartao));
            }catch (FeignException e){
                e.printStackTrace();
                System.out.println("Erro no processamento do cartao");
            }
        }
    }
}
