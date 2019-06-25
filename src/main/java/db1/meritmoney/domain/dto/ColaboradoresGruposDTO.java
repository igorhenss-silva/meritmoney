package db1.meritmoney.domain.dto;

import java.io.Serializable;
import java.util.Objects;

public class ColaboradoresGruposDTO implements Serializable {

    public static final long serialVersionUID = 23L;

    private Long id;
    private ColaboradorDTO colaborador;
    private GrupoDTO grupo;
    private Boolean responsavel;

    public ColaboradoresGruposDTO(Long id, ColaboradorDTO colaborador, GrupoDTO grupo, Boolean responsavel) {
        this.id = id;
        this.colaborador = colaborador;
        this.grupo = grupo;
        this.responsavel = responsavel;
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

    public ColaboradorDTO getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorDTO colaborador) {
        this.colaborador = colaborador;
    }

    public GrupoDTO getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoDTO grupo) {
        this.grupo = grupo;
    }

    public Boolean getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Boolean responsavel) {
        this.responsavel = responsavel;
    }

}
