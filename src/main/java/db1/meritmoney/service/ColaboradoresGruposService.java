package db1.meritmoney.service;

import db1.meritmoney.domain.dto.ColaboradoresGruposDTO;
import db1.meritmoney.domain.entity.Colaborador;
import db1.meritmoney.domain.entity.ColaboradoresGrupos;
import db1.meritmoney.domain.entity.Grupo;
import db1.meritmoney.repository.ColaboradorRepository;
import db1.meritmoney.repository.ColaboradoresGruposRepository;
import db1.meritmoney.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradoresGruposService {

    @Autowired
    ColaboradoresGruposRepository colaboradoresGruposRepository;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Autowired
    GrupoRepository grupoRepository;

    // CREATE
    public ColaboradoresGruposDTO save(ColaboradoresGruposDTO dto) {
        Colaborador colaborador = colaboradorRepository.getOne(dto.getColaborador());
        Grupo grupo = grupoRepository.getOne(dto.getGrupo());
        ColaboradoresGrupos colaboradoresGrupos = new ColaboradoresGrupos(colaborador, grupo, dto.getResponsavel());
        return colaboradoresGruposToDTO(colaboradoresGruposRepository.save(colaboradoresGrupos));
    }

    // READ

    // UPDATE

    // DELETE

    private ColaboradoresGruposDTO colaboradoresGruposToDTO(ColaboradoresGrupos colaboradoresGrupos) {
        return new ColaboradoresGruposDTO(colaboradoresGrupos.getId(), colaboradoresGrupos.getColaborador().getId(),
                colaboradoresGrupos.getGrupo().getId(), colaboradoresGrupos.getResponsavel());
    }

}
