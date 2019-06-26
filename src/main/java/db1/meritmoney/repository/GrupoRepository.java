package db1.meritmoney.repository;

import db1.meritmoney.domain.dto.GrupoDTO;
import db1.meritmoney.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    List<GrupoDTO> findByNome(String nome);

}
