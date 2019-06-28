package db1.meritmoney.domain.entity;

import db1.meritmoney.enums.TipoTransacao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_origem", referencedColumnName = "id", nullable = false)
    private Colaborador colaboradorOrigem;

    @Column(name = "quantia_transferida", nullable = false)
    private Double quantiaTransferida;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transacao", length = 1, nullable = false)
    private TipoTransacao tipoTransacao;

    @ManyToOne
    @JoinColumn(name = "colaborador_destino", referencedColumnName = "id", nullable = false)
    private Colaborador colaboradorDestino;

    @Column(name = "data_transacao", nullable = false)
    private LocalDate dataTransacao;

    @ManyToOne
    @JoinColumn(name = "grupo_origem", referencedColumnName = "id", nullable = false)
    private Grupo grupoOrigem;

    protected Transacao() {}

    public Transacao(Colaborador colaboradorOrigem, Double quantiaTransferida, TipoTransacao tipoTransacao,
                     Colaborador colaboradorDestino, Grupo grupoOrigem) {
        if (tipoTransacao.equals(TipoTransacao.S)) {
            colaboradorOrigem.temSaldoSuficiente(quantiaTransferida);
        } else {
            colaboradorOrigem.isGestor();
        }
        colaboradorOrigemDeveSerDiferenteDoUsuarioDestino(colaboradorOrigem, colaboradorDestino);
        setColaboradorOrigem(colaboradorOrigem);
        setQuantiaTransferida(quantiaTransferida);
        setTipoTransacao(tipoTransacao);
        setColaboradorDestino(colaboradorDestino);
        setDataTransacao(LocalDate.now());
        setGrupoOrigem(grupoOrigem);
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

    public Colaborador getColaboradorOrigem() {
        return colaboradorOrigem;
    }

    public void setColaboradorOrigem(Colaborador colaboradorOrigem) {
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

    public Colaborador getColaboradorDestino() {
        return colaboradorDestino;
    }

    public void setColaboradorDestino(Colaborador colaboradorDestino) {
        this.colaboradorDestino = colaboradorDestino;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Grupo getGrupoOrigem() {
        return grupoOrigem;
    }

    public void setGrupoOrigem(Grupo grupoOrigem) {
        this.grupoOrigem = grupoOrigem;
    }

}
