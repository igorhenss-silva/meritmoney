package db1.meritmoney.repository;

import db1.meritmoney.domain.entity.Grupo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrupoRepositoryTest {

    final static String nomeExistente = "DB1";
    final static String nomeInexistente = "Grupo não existente no BD";

    @Autowired
    GrupoRepository grupoRepository;

    private Grupo grupo = new Grupo(nomeExistente, LocalDate.parse("2000-01-01"), LocalDate.parse("3000-01-01"));

    @Before
    public void before() {
        grupoRepository.save(grupo);
    }

    @Test
    public void deveRetornarUmaListaDeGruposCorrespondentesAoNomeQueFoiPassado() {
        List<Grupo> grupos = grupoRepository.findByNome(nomeExistente);
        assertTrue(grupos.stream()
                .allMatch(grupo -> grupo.getNome()
                        .equals(nomeExistente)));
        assertEquals(1, grupos.size());
    }

    @Test
    public void deveRetornarUmaListaDeGruposVaziaPoisONomePassadoNaoExiste() {
        List<Grupo> grupos = grupoRepository.findByNome(nomeInexistente);
        assertTrue(grupos.isEmpty());
    }

    @After
    public void after() {
        grupoRepository.deleteAll();
    }

}
