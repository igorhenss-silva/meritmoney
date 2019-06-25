package db1.meritmoney.service;

import db1.meritmoney.domain.dto.GrupoDTO;
import db1.meritmoney.domain.entity.Grupo;
import db1.meritmoney.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    // CREATE

    public GrupoDTO save(GrupoDTO dto) {
        Grupo grupo = new Grupo(dto.getNome(), dto.getDataFinalizacao());
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
                        || grupo.getDataFinalizacao().equals(data))
                .map(grupo -> grupoToDTO(grupo))
                .collect(Collectors.toList());
    }

    // UPDATE

    public GrupoDTO put(Long id, GrupoDTO dto) {
        Grupo grupoParaAtualizar = grupoRepository.getOne(id);
        grupoParaAtualizar.setNome(dto.getNome());
        grupoParaAtualizar.setDataInicio(dto.getDataInicio());
        grupoParaAtualizar.setDataFinalizacao(dto.getDataFinalizacao());
        grupoRepository.save(grupoParaAtualizar);
        return grupoToDTO(grupoParaAtualizar);
    }

    // DELETE

    public void delete(Long id) {
        Grupo grupoParaDeletar= grupoRepository.getOne(id);
        grupoRepository.delete(grupoParaDeletar);
        grupoRepository.save(grupoParaDeletar);
    }

    private GrupoDTO grupoToDTO(Grupo grupo) {
        return new GrupoDTO(grupo.getId(), grupo.getNome(), grupo.getDataInicio(), grupo.getDataFinalizacao());
    }

}