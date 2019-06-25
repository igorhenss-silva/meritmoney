package db1.meritmoney.repository;

import db1.meritmoney.domain.dto.ColaboradorDTO;
import db1.meritmoney.domain.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    ColaboradorDTO findByUsuario(String usuario);
    List<ColaboradorDTO> findByNome(String nome);
    List<ColaboradorDTO> findBySaldo(Double saldo);

}
