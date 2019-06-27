//package db1.meritmoney.domain.entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "colaboradores_grupos")
//public class ColaboradoresGrupos {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "colaborador", referencedColumnName = "id", nullable = false)
//    private Colaborador colaborador;
//
//    @ManyToOne
//    @JoinColumn(name = "grupo", referencedColumnName = "id", nullable = false)
//    private Grupo grupo;
//
//    @Column(name = "responsavel", nullable = false)
//    private Boolean responsavel;
//
//    protected ColaboradoresGrupos() {}
//
//    public ColaboradoresGrupos(Colaborador colaborador, Grupo grupo, Boolean responsavel) {
//        setGrupo(grupo);
//        setColaborador(colaborador);
//        setResponsavel(responsavel);
//    }
//
//    // METHODS
//
//    // GETTERS AND SETTERS
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Grupo getGrupo() {
//        return grupo;
//    }
//
//    public void setGrupo(Grupo grupo) {
//        this.grupo = grupo;
//    }
//
//    public Colaborador getColaborador() {
//        return colaborador;
//    }
//
//    public void setColaborador(Colaborador colaborador) {
//        this.colaborador = colaborador;
//    }
//
//    public Boolean getResponsavel() {
//        return responsavel;
//    }
//
//    public void setResponsavel(Boolean responsavel) {
//        responsavel = responsavel;
//    }
//}
