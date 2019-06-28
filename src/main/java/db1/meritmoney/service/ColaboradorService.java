package db1.meritmoney.service;

import db1.meritmoney.domain.dto.ColaboradorDTO;
import db1.meritmoney.domain.entity.Colaborador;
import db1.meritmoney.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    // CREATE

    public ColaboradorDTO save(ColaboradorDTO dto) {
        usuarioJaExiste(dto);
        Colaborador colaborador = new Colaborador(dto.getNome(), dto.getUsuario(), dto.getGestor());
        colaborador.setSaldo(dto.getSaldo());
        colaborador = colaboradorRepository.save(colaborador);
        return colaboradorToDTO(colaborador);
    }

    // READ

    public ColaboradorDTO getByUsuario(String usuario) {
        return colaboradorToDTO(colaboradorRepository.findByUsuario(usuario));
    }

    public List<ColaboradorDTO> getByNome(String nome) {
        return colaboradorRepository.findAll()
                .stream()
                .filter(colaborador -> colaborador.getNome().contains(nome))
                .map(colaborador -> colaboradorToDTO(colaborador))
                .collect(Collectors.toList());
    }

    public List<ColaboradorDTO> getBySaldo(Double saldo) {
        return colaboradorRepository.findAll()
                .stream()
                .filter(colaborador -> colaborador.getSaldo() > saldo)
                .map(colaborador -> colaboradorToDTO(colaborador))
                .collect(Collectors.toList());
    }

    // UPDATE

    public ColaboradorDTO put(Long id, ColaboradorDTO dto) {
        Colaborador colaboradorParaAtualizar = colaboradorRepository.getOne(id);
        colaboradorParaAtualizar.setSaldo(dto.getSaldo());
        colaboradorRepository.save(colaboradorParaAtualizar);
        return colaboradorToDTO(colaboradorParaAtualizar);
    }

    // DELETE

    public void delete(Long id) {
        colaboradorRepository.delete(colaboradorRepository.getOne(id));
    }

    // METHODS

    private void usuarioJaExiste(ColaboradorDTO dto) {
        List<ColaboradorDTO> colaboradores = colaboradorRepository.findAll()
                .stream()
                .map(colaborador -> colaboradorToDTO(colaborador))
                .filter(colaborador -> colaborador.getUsuario().equals(dto.getUsuario()))
                .collect(Collectors.toList());
        if (colaboradores.size() != 0) {
            throw new RuntimeException("O usuário do colaborador já está em uso!");
        }
    }

    private ColaboradorDTO colaboradorToDTO(Colaborador colaborador) {
        return new ColaboradorDTO(colaborador.getId(), colaborador.getNome(), colaborador.getUsuario(), colaborador.getSaldo(), colaborador.getGestor());
    }

}
