package db1.meritmoney.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class GrupoDTO implements Serializable {

    public static final long serialVersionUID = 23L;

    private Long id;
    private String nome;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinalizacao;

    public GrupoDTO(Long id, String nome, LocalDateTime dataInicio, LocalDateTime dataFinalizacao) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFinalizacao = dataFinalizacao;
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
        if (!(obj instanceof GrupoDTO)) {
            return false;
        }
        GrupoDTO other = (GrupoDTO) obj;
        return Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
                && Objects.equals(dataInicio, other.dataInicio) && Objects.equals(dataFinalizacao, other.dataFinalizacao);
    }

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }
}
