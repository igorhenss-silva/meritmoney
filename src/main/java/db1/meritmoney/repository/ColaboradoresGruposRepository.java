package db1.meritmoney.repository;

import db1.meritmoney.domain.entity.Colaborador;
import db1.meritmoney.domain.entity.ColaboradoresGrupos;
import db1.meritmoney.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColaboradoresGruposRepository extends JpaRepository<ColaboradoresGrupos, Long> {

    List<ColaboradoresGrupos> findByColaborador(Colaborador colaborador);
    List<ColaboradoresGrupos> findByGrupo(Grupo grupo);
    List<ColaboradoresGrupos> findByResponsavel(Boolean isResponsavel);

}
