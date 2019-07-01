package db1.meritmoney.service;

import db1.meritmoney.domain.dto.GrupoDTO;
import db1.meritmoney.domain.entity.Grupo;
import db1.meritmoney.repository.GrupoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrupoServiceTest {

    private final static String nomePassado = "Gru";
    private final static LocalDate dataInicioPassada = LocalDate.parse("2005-01-01");
    private final static LocalDate dataEncerramentoPassada = LocalDate.parse("3000-05-11");

    @Autowired
    GrupoService grupoService;

    @Autowired
    GrupoRepository grupoRepository;

    private GrupoDTO grupoParaSalvarAntes = new GrupoDTO(1L, "Grupo Salvo em @Before", LocalDate.parse("2000-01-01"), LocalDate.parse("3000-03-12"));
    private GrupoDTO grupoParaSalvarEmTeste = new GrupoDTO(2L, "Grupo Salvo em @Test", LocalDate.parse("2011-02-08"), LocalDate.parse("3000-05-11"));
    private GrupoDTO grupoComNomeRepetido = new GrupoDTO(3L, "Grupo Salvo em @Before", LocalDate.parse("2000-12-12"), LocalDate.parse("3000-06-01"));

    @Before
    public void before() {
        grupoService.save(grupoParaSalvarAntes);
    }

    // CREATE

    @Test
    public void deveSalvarUmColaborador() {
        grupoService.save(grupoParaSalvarEmTeste);
        assertEquals(2, grupoRepository.findAll().size());
    }

    @Test
    public void deveRetornarRuntimeExceptionNomeDoGrupoJaEstaEmUso() {
        try {
            grupoService.save(grupoComNomeRepetido);
        } catch (RuntimeException e) {
            assertEquals("O nome do grupo já está em uso!", e.getMessage());
        }
    }

    // READ

    @Test
    public void deveRetornarUmaListaDeGruposComNomesQueContenhamONomePassado() {
        List<GrupoDTO> grupos = grupoService.getByNome(nomePassado);
        assertTrue(grupos.stream()
                .allMatch(grupo -> grupo.getNome()
                        .contains(nomePassado)));
        assertEquals(1, grupos.size());
    }

    @Test
    public void deveRetornarUmaListaVaziaPoisNenhumNomeDeGrupoContemONomePassado() {
        List<GrupoDTO> grupos = grupoService.getByNome("XYZ");
        assertTrue(grupos.isEmpty());
    }

    @Test
    public void deveRetornarUmaListaDeGruposAbertosEntreAsDuasDatasPassadas() {
        grupoService.save(grupoParaSalvarEmTeste);
        List<GrupoDTO> grupos = grupoService.getByData(dataInicioPassada, LocalDate.parse("3000-05-11"));
        assertTrue(grupos.stream().allMatch(grupo -> (grupo.getDataInicio().isEqual(dataInicioPassada) || grupo.getDataInicio().isAfter(dataInicioPassada))
                && (grupo.getDataEncerramento().isEqual(dataEncerramentoPassada) || grupo.getDataEncerramento().isBefore(dataEncerramentoPassada))));
        assertEquals(1, grupos.size());
    }

    @Test
    public void deveRetornarUmaListaVaziaPoisNaoHaGrupoEntreAsDatas() {
        List<GrupoDTO> grupos = grupoService.getByData(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-05-11"));
        assertTrue(grupos.isEmpty());
    }

    // UPDATE

    // DELETE

    @Test
    public void deletarGrupoSalvo() {
        List<GrupoDTO> grupoParaDeletar = grupoService.getByNome(grupoParaSalvarAntes.getNome());
        grupoService.delete(grupoParaDeletar.get(0).getId());
        assertTrue(grupoRepository.findAll().isEmpty());
    }

    @Test
    public void deletarGrupoInexistente() {
        try {
            grupoService.delete(50L);
        } catch (JpaObjectRetrievalFailureException e) { }
    }

    @After
    public void after() {
        grupoRepository.deleteAll();
    }

}
