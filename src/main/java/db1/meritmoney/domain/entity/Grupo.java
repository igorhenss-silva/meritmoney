package db1.meritmoney.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_encerramento", nullable = false)
    private LocalDate dataEncerramento;

    protected Grupo() {}

    public Grupo(String nome, LocalDate dataInicio, LocalDate dataEncerramento) {
        verificaNome(nome);
        verificaDatas(dataInicio, dataEncerramento);
        setNome(nome);
        setDataInicio(dataInicio);
        setDataEncerramento(dataEncerramento);
    }

    // METHODS

    public void encerrar() {
        dataEncerramento = LocalDate.now();
    }

    private void verificaNome(String nome) {
        if (nome.length() < 3) {
            throw new RuntimeException("O nome do grupo deve conter três ou mais caracteres.");
        }
    }

    private void verificaDatas(LocalDate dataInicio, LocalDate dataEncerramento) {
        if (dataInicio.isAfter(dataEncerramento)) {
            throw new RuntimeException("A data de encerramento deve ser maior que a data de início.");
        }
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
