package db1.meritmoney.repository;

import db1.meritmoney.domain.dto.ColaboradorDTO;
import db1.meritmoney.domain.dto.GrupoDTO;
import db1.meritmoney.domain.entity.ColaboradoresGrupos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColaboradoresGruposRepository extends JpaRepository<ColaboradoresGrupos, Long> {

    List<ColaboradoresGrupos> findByColaborador(Long colaborador);
    List<ColaboradoresGrupos> findByGrupo(Long grupo);
    List<ColaboradoresGrupos> findByResponsavel(Boolean isResponsavel);

}
