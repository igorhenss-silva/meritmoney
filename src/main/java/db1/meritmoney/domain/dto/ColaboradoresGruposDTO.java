package db1.meritmoney.domain.dto;

import java.io.Serializable;
import java.util.Objects;

public class ColaboradoresGruposDTO implements Serializable {

    public static final long serialVersionUID = 23L;

    private Long id;
    private Long colaborador;
    private Long grupo;
    private Boolean responsavel;

    public ColaboradoresGruposDTO(Long id, Long colaborador, Long grupo, Boolean responsavel) {
        setId(id);
        setColaborador(colaborador);
        setGrupo(grupo);
        setResponsavel(responsavel);
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
        if (!(obj instanceof ColaboradoresGruposDTO)) {
            return false;
        }
        ColaboradoresGruposDTO other = (ColaboradoresGruposDTO) obj;
        return Objects.equals(id, other.id) && Objects.equals(colaborador, other.colaborador)
                && Objects.equals(grupo, other.grupo) && Objects.equals(responsavel, other.responsavel);
    }

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getColaborador() {
        return colaborador;
    }

    public void setColaborador(Long colaborador) {
        this.colaborador = colaborador;
    }

    public Long getGrupo() {
        return grupo;
    }

    public void setGrupo(Long grupo) {
        this.grupo = grupo;
    }

    public Boolean getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Boolean responsavel) {
        this.responsavel = responsavel;
    }

}
