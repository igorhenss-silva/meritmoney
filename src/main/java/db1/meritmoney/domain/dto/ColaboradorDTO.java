package db1.meritmoney.domain.dto;

import java.io.Serializable;
import java.util.Objects;

public class ColaboradorDTO implements Serializable {

    public static final long serialVersionUID = 23L;

    private Long id;
    private String nome;
    private String usuario;
    private Double saldo;
    private Boolean gestor;

    public ColaboradorDTO(Long id, String nome, String usuario, Double saldo, Boolean gestor) {
        setId(id);
        setNome(nome);
        setUsuario(usuario);
        setSaldo(saldo);
        setGestor(gestor);
    }

    // METHODS

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ColaboradorDTO)) {
            return false;
        }
        ColaboradorDTO other = (ColaboradorDTO) obj;
        return Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
                && Objects.equals(usuario, other.usuario) && Objects.equals(saldo, other.saldo);
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getGestor() {
        return gestor;
    }

    public void setGestor(Boolean gestor) {
        this.gestor = gestor;
    }
}
