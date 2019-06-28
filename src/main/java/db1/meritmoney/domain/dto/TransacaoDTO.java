package db1.meritmoney.domain.dto;

import db1.meritmoney.enums.TipoTransacao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TransacaoDTO implements Serializable {

    public static final long serialVersionUID = 23L;

    private Long id;
    private Long colaboradorOrigem;
    private Double quantiaTransferida;
    private TipoTransacao tipoTransacao;
    private Long colaboradorDestino;
    private LocalDate dataTransacao;
    private Long grupoOrigem;

    public TransacaoDTO(Long id, Long colaboradorOrigem, Double quantiaTransferida,
                        TipoTransacao tipoTransacao, Long colaboradorDestino,
                        LocalDate dataTransacao, Long grupoOrigem) {
        setId(id);
        setColaboradorOrigem(colaboradorOrigem);
        setQuantiaTransferida(quantiaTransferida);
        setTipoTransacao(tipoTransacao);
        setColaboradorDestino(colaboradorDestino);
        setDataTransacao(dataTransacao);
        setGrupoOrigem(grupoOrigem);
    }

    // METHODS

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TransacaoDTO)) {
            return false;
        }
        TransacaoDTO other = (TransacaoDTO) obj;
        return Objects.equals(id, other.id) && Objects.equals(colaboradorOrigem, other.colaboradorOrigem)
                && Objects.equals(quantiaTransferida, other.quantiaTransferida) && Objects.equals(tipoTransacao, other.tipoTransacao)
                && Objects.equals(colaboradorDestino, other.colaboradorDestino) && Objects.equals(dataTransacao, other.dataTransacao)
                && Objects.equals(grupoOrigem, other.grupoOrigem);
    }

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getColaboradorOrigem() {
        return colaboradorOrigem;
    }

    public void setColaboradorOrigem(Long colaboradorOrigem) {
        this.colaboradorOrigem = colaboradorOrigem;
    }

    public Double getQuantiaTransferida() {
        return quantiaTransferida;
    }

    public void setQuantiaTransferida(Double quantiaTransferida) {
        this.quantiaTransferida = quantiaTransferida;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Long getColaboradorDestino() {
        return colaboradorDestino;
    }

    public void setColaboradorDestino(Long colaboradorDestino) {
        this.colaboradorDestino = colaboradorDestino;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Long getGrupoOrigem() {
        return grupoOrigem;
    }

    public void setGrupoOrigem(Long grupoOrigem) {
        this.grupoOrigem = grupoOrigem;
    }

}
