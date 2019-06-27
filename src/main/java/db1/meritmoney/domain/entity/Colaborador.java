package db1.meritmoney.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "usuario", unique = true, nullable = false)
    private String usuario;

    @Column(name = "saldo", nullable = false)
    private Double saldo;

    @Column(name = "gestor", nullable = false)
    private Boolean gestor;

    protected Colaborador() {}

    public Colaborador(String nome, String usuario, Boolean gestor) {
        setNome(nome);
        setUsuario(usuario);
        setSaldo(0.0);
        setGestor(gestor);
    }

    // METHODS

    public void temSaldoSuficiente(Double quantiaTransferida) {
        if (saldo < quantiaTransferida) {
            throw new RuntimeException("Saldo insuficiente.");
        }
    }

    public void isGestor() {
        if (!gestor) {
            throw new RuntimeException("Somente gestores podem realizar essa operação.");
        }
    }

    public void transferir(Double saldo) {
        temSaldoSuficiente(saldo);
        this.saldo -= saldo;
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
