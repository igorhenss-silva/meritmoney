package db1.meritmoney.repository;

import db1.meritmoney.domain.entity.Grupo;
import db1.meritmoney.domain.entity.Transacao;
import db1.meritmoney.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByTipoTransacao(TipoTransacao tipoTransacao);
    List<Transacao> findByDataTransacao(LocalDate dataTransacao);
    List<Transacao> findByGrupoOrigem(Grupo grupoOrigem);

}
