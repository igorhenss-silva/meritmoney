package db1.meritmoney.service;

import db1.meritmoney.domain.dto.GrupoDTO;
import db1.meritmoney.domain.entity.Grupo;
import db1.meritmoney.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    // CREATE

    public GrupoDTO save(GrupoDTO dto) {
        nomeJaExiste(dto);
        dataInicioValida(dto);
        dataEncerramentoValida(dto);
        Grupo grupo = new Grupo(dto.getNome(), dto.getDataInicio(), dto.getDataEncerramento());
        grupo = grupoRepository.save(grupo);
        return grupoToDTO(grupo);
    }

    // READ

    public List<GrupoDTO> getByNome(String nome) {
        return grupoRepository.findAll()
                .stream()
                .filter(grupo -> grupo.getNome().contains(nome))
                .map(grupo -> grupoToDTO(grupo))
                .collect(Collectors.toList());
    }

    public List<GrupoDTO> getByData(LocalDateTime data) {
        return grupoRepository.findAll()
                .stream()
                .filter(grupo -> grupo.getDataInicio().equals(data)
                        || grupo.getDataEncerramento().equals(data))
                .map(grupo -> grupoToDTO(grupo))
                .collect(Collectors.toList());
    }

    // UPDATE

    public GrupoDTO put(Long id, GrupoDTO dto) {
        Grupo grupoParaAtualizar = grupoRepository.getOne(id);
        grupoParaAtualizar.setNome(dto.getNome());
        grupoParaAtualizar.setDataInicio(dto.getDataInicio());
        grupoParaAtualizar.setDataEncerramento(dto.getDataEncerramento());
        grupoRepository.save(grupoParaAtualizar);
        return grupoToDTO(grupoParaAtualizar);
    }

    // DELETE

    public void delete(Long id) {
        grupoRepository.delete(grupoRepository.getOne(id));
    }

    // METHODS

    private GrupoDTO grupoToDTO(Grupo grupo) {
        return new GrupoDTO(grupo.getId(), grupo.getNome(),
                grupo.getDataInicio(), grupo.getDataEncerramento());
    }

    private void nomeJaExiste(GrupoDTO grupo) {
        if (!grupoRepository.findByNome(grupo.getNome()).isEmpty()) {
            throw new RuntimeException("O nome do grupo já está em uso!");
        }
    }

    private void dataInicioValida(GrupoDTO grupo) {
        try {
            grupo.getDataInicio().toString().isEmpty();
        } catch (NullPointerException e) {
            throw new RuntimeException("A data de início deve ser definida.");
        }
    }

    private void dataEncerramentoValida(GrupoDTO grupo) {
        try {
            grupo.getDataEncerramento().toString().isEmpty();
        }  catch (NullPointerException e) {
            grupo.setDataEncerramento(LocalDate.parse("3000-01-01"));
        } finally {
            dataDeEncerramentoMaiorQueInicial(grupo);
        }
    }

    private void dataDeEncerramentoMaiorQueInicial(GrupoDTO grupo) {
        if (grupo.getDataEncerramento().isBefore(grupo.getDataInicio())) {
            throw new RuntimeException("A data de encerramento do grupo deve ser maior que a data de início.");
        }
    }

}