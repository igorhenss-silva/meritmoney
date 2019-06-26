package db1.meritmoney.service;

import db1.meritmoney.domain.dto.TransacaoDTO;
import db1.meritmoney.domain.entity.Colaborador;
import db1.meritmoney.domain.entity.Grupo;
import db1.meritmoney.domain.entity.Transacao;
import db1.meritmoney.enums.TipoTransacao;
import db1.meritmoney.repository.ColaboradorRepository;
import db1.meritmoney.repository.GrupoRepository;
import db1.meritmoney.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @Autowired
    GrupoRepository grupoRepository;

    // CREATE
    public TransacaoDTO save(TransacaoDTO dto) {
        Colaborador colaboradorOrigem = colaboradorRepository.getOne(dto.getColaboradorOrigem());
        Colaborador colaboradorDestino = colaboradorRepository.getOne(dto.getColaboradorDestino());
        Grupo grupoOrigem = grupoRepository.getOne(dto.getGrupoOrigem());
        Transacao transacao = new Transacao(colaboradorOrigem, dto.getQuantiaTransferida(), dto.getTipoTransacao(), colaboradorDestino, grupoOrigem);
        transacao = transacaoRepository.save(transacao);
        if (dto.getTipoTransacao().equals(TipoTransacao.S)) {
            colaboradorOrigem.setSaldo(colaboradorOrigem.getSaldo() - dto.getQuantiaTransferida());
        } else {
            colaboradorOrigem.setSaldo(dto.getQuantiaTransferida());
        }
        colaboradorRepository.save(colaboradorOrigem);
        return transacaoToDTO(transacao);
    }

    // READ

    public List<TransacaoDTO> getByColaboradorOrigem(Long idColaboradorOrigem) {
        return transacaoRepository.findByColaboradorOrigem(idColaboradorOrigem);
    }

    public List<TransacaoDTO> getByColaboradorDestino(Long idColaboradorDestino) {
        return transacaoRepository.findByColaboradorDestino(idColaboradorDestino);
    }

    public List<TransacaoDTO> getByColaborador(Long id) {
        List<TransacaoDTO> colaboradores = new ArrayList<>();
        getByColaboradorOrigem(id).stream().map(colaborador -> colaboradores.add(colaborador));
        getByColaboradorDestino(id).stream().map(colaborador -> colaboradores.add(colaborador));
        return colaboradores;
    }

    public List<TransacaoDTO> getByQuantiaTransferida(Double quantiaMin, Double quantiaMax) {
        return transacaoRepository.findAll()
                .stream()
                .filter(transacao -> transacao.getQuantiaTransferida() >= quantiaMin && transacao.getQuantiaTransferida() <= quantiaMax)
                .map(transacao -> transacaoToDTO(transacao))
                .collect(Collectors.toList());
    }

    public List<TransacaoDTO> getByTipoTransacao(TipoTransacao tipoTransacao) {
        return transacaoRepository.findByTipoTransacao(tipoTransacao);
    }

    public List<TransacaoDTO> getByDataTransacao(LocalDate dataTransacao) {
        return transacaoRepository.findByDataTransacao(dataTransacao);
    }

    public List<TransacaoDTO> getByGrupoOrigem(Long grupoOrigem) {
        return transacaoRepository.findByGrupoOrigem(grupoOrigem);
    }

    // UPDATE

    public TransacaoDTO put(Long id, TransacaoDTO dto) {
        Transacao transacaoParaAtualizar = transacaoRepository.getOne(id);
        Colaborador colaboradorOrigem = colaboradorRepository.getOne(dto.getColaboradorOrigem());
        Colaborador colaboradorDestino = colaboradorRepository.getOne(dto.getColaboradorDestino());
        Grupo grupoOrigem = grupoRepository.getOne(dto.getGrupoOrigem());

        transacaoParaAtualizar.setColaboradorOrigem(colaboradorOrigem);
        transacaoParaAtualizar.setQuantiaTransferida(dto.getQuantiaTransferida());
        transacaoParaAtualizar.setTipoTransacao(dto.getTipoTransacao());
        transacaoParaAtualizar.setColaboradorDestino(colaboradorDestino);
        transacaoParaAtualizar.setDataTransacao(dto.getDataTransacao());
        transacaoParaAtualizar.setGrupoOrigem(grupoOrigem);
        transacaoRepository.save(transacaoParaAtualizar);
        return transacaoToDTO(transacaoParaAtualizar);
    }

    // DELETE

    public void delete(Long id) {
        transacaoRepository.delete(transacaoRepository.getOne(id));
    }

    // METHODS

    private TransacaoDTO transacaoToDTO(Transacao transacao) {
        return new TransacaoDTO(transacao.getId(), transacao.getColaboradorOrigem().getId(),
                transacao.getQuantiaTransferida(), transacao.getTipoTransacao(),
                transacao.getColaboradorDestino().getId(), transacao.getDataTransacao(),
                transacao.getGrupoOrigem().getId());
    }

}
