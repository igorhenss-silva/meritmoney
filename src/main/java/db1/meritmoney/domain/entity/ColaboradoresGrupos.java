package db1.meritmoney.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "colaboradores_grupos")
public class ColaboradoresGrupos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grupo", nullable = false)
    private Long grupo;

    @Column(name = "colaborador", nullable = false)
    private Long colaborador;

    @Column(name = "responsavel", nullable = false)
    private Boolean responsavel;

    protected ColaboradoresGrupos() {}

    public ColaboradoresGrupos(Grupo grupo, Colaborador colaborador, Boolean responsavel) {
        this.grupo = grupo.getId();
        this.colaborador = colaborador.getId();
        this.responsavel = responsavel;
    }

    // METHODS

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGrupo() {
        return grupo;
    }

    public void setGrupo(Long grupo) {
        this.grupo = grupo;
    }

    public Long getColaborador() {
        return colaborador;
    }

    public void setColaborador(Long colaborador) {
        this.colaborador = colaborador;
    }

    public Boolean getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Boolean responsavel) {
        responsavel = responsavel;
    }
}
