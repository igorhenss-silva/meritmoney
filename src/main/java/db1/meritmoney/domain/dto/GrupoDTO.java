package db1.meritmoney.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class GrupoDTO implements Serializable {

    public static final long serialVersionUID = 23L;

    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataEncerramento;

    public GrupoDTO(Long id, String nome, LocalDate dataInicio, LocalDate dataEncerramento) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataEncerramento = dataEncerramento;
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
                && Objects.equals(dataInicio, other.dataInicio) && Objects.equals(dataEncerramento, other.dataEncerramento);
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDate dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }
}
