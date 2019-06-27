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
import java.time.LocalDateTime;
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
        if (dto.getTipoTransacao().equals(TipoTransacao.S)) {
            colaboradorOrigem.transferir(dto.getQuantiaTransferida());
        } else if (dto.getTipoTransacao().equals(TipoTransacao.E)) {
            colaboradorOrigem.isGestor();
        }
        colaboradorDestino.setSaldo(colaboradorDestino.getSaldo() + dto.getQuantiaTransferida());
        colaboradorRepository.save(colaboradorOrigem);
        colaboradorRepository.save(colaboradorDestino);
        return transacaoToDTO(transacaoRepository.save(transacao));
    }

    // READ

    public List<TransacaoDTO> getByColaborador(Long id) {
        return transacaoRepository.findAll()
                .stream()
                .map(transacao -> transacaoToDTO(transacao))
                .filter(transacao -> transacao.getColaboradorOrigem().equals(id)
                        || transacao.getColaboradorDestino().equals(id))
                .collect(Collectors.toList());
    }

    public List<TransacaoDTO> getByQuantiaTransferida(Double quantiaMin, Double quantiaMax) {
        return transacaoRepository.findAll()
                .stream()
                .filter(transacao -> transacao.getQuantiaTransferida() >= quantiaMin && transacao.getQuantiaTransferida() <= quantiaMax)
                .map(transacao -> transacaoToDTO(transacao))
                .collect(Collectors.toList());
    }

    public List<TransacaoDTO> getByTipoTransacao(TipoTransacao tipoTransacao) {
        return transacaoRepository.findByTipoTransacao(tipoTransacao)
                .stream()
                .map(transacao -> transacaoToDTO(transacao))
                .collect(Collectors.toList());
    }

    public List<TransacaoDTO> getByDataTransacao(LocalDate dataTransacao) {
        return transacaoRepository.findByDataTransacao(dataTransacao)
                .stream()
                .map(transacao -> transacaoToDTO(transacao))
                .collect(Collectors.toList());
    }

    public List<TransacaoDTO> getByGrupoOrigem(Long grupoOrigem) {
        return transacaoRepository.findByGrupoOrigem(grupoOrigem)
                .stream()
                .map(transacao -> transacaoToDTO(transacao))
                .collect(Collectors.toList());
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
