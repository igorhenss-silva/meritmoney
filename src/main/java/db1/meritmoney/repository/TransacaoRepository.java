package db1.meritmoney.repository;

import db1.meritmoney.domain.dto.TransacaoDTO;
import db1.meritmoney.domain.entity.Transacao;
import db1.meritmoney.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<TransacaoDTO> findByColaboradorOrigem(Long colaboradorOrigem);
    List<TransacaoDTO> findByTipoTransacao(TipoTransacao tipoTransacao);
    List<TransacaoDTO> findByColaboradorDestino(Long colaboradorDestino);
    List<TransacaoDTO> findByDataTransacao(LocalDate dataTransacao);
    List<TransacaoDTO> findByGrupoOrigem(Long grupoOrigem);

}
