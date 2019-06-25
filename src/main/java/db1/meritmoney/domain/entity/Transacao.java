package db1.meritmoney.domain.entity;

import db1.meritmoney.enums.TipoTransacao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "colaborador_origem", nullable = false)
    private Long colaboradorOrigem;

    @Column(name = "quantia_transferida", nullable = false)
    private Double quantiaTransferida;

    @Column(name = "tipo_transacao", length = 1, nullable = false)
    private TipoTransacao tipoTransacao;

    @Column(name = "colaborador_destino", nullable = false)
    private Long colaboradorDestino;

    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime dataTransacao;

    @Column(name = "grupo_origem", nullable = false)
    private Long grupoOrigem;


    public Transacao(Colaborador colaboradorOrigem, Double quantiaTransferida, TipoTransacao tipoTransacao,
                     Colaborador colaboradorDestino, Grupo grupoOrigem) {
        colaboradorOrigem.temSaldoSuficiente(quantiaTransferida);
        colaboradorOrigemDeveSerDiferenteDoUsuarioDestino(colaboradorOrigem, colaboradorDestino);
        this.colaboradorOrigem = colaboradorOrigem.getId();
        this.quantiaTransferida = quantiaTransferida;
        this.tipoTransacao = tipoTransacao;
        this.colaboradorDestino = colaboradorDestino.getId();
        this.dataTransacao = LocalDateTime.now();
        this.grupoOrigem = grupoOrigem.getId();
    }

    // METHODS

    private void colaboradorOrigemDeveSerDiferenteDoUsuarioDestino(Colaborador colaboradorOrigem, Colaborador colaboradorDestino) {
        if (colaboradorOrigem.equals(colaboradorDestino)) {
            throw new RuntimeException("Os colaboradores de origem e destino devem ser diferentes.");
        }
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

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Long getGrupoOrigem() {
        return grupoOrigem;
    }

    public void setGrupoOrigem(Long grupoOrigem) {
        this.grupoOrigem = grupoOrigem;
    }

}
